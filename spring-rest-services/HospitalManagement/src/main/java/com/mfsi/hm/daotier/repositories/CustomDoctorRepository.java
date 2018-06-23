/**
 * 
 */
package com.mfsi.hm.daotier.repositories;

import static com.mfsi.hm.core.common.Constants.MM_DD_YYYY_HH_MM_SS_DATE_FORMAT;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

import com.mfsi.hm.core.pagination.SearchSortInfoVO;
import com.mfsi.hm.core.util.DateUtil;
import com.mfsi.hm.daotier.models.Doctor;

/**
 * @author shah
 *
 */
public class CustomDoctorRepository {
	
	@Autowired
	private EntityManagerFactory entityManagerFactory;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Doctor> getAuditListwithSort(Pageable pageRequest,
			Map<String,SearchSortInfoVO> searchParamsInfoMap) {
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Object> criteriaQuery = criteriaBuilder.createQuery(Object.class);
		Root<Doctor> root = criteriaQuery.from(Doctor.class);

		criteriaQuery = getSearchSortCriteriaQuery(criteriaBuilder, criteriaQuery, root, pageRequest, searchParamsInfoMap);
		
		TypedQuery typedQuery = entityManager.createQuery(criteriaQuery.select(root).distinct(true))
				.setFirstResult((int) pageRequest.getOffset()).setMaxResults(pageRequest.getPageSize());
		return typedQuery.getResultList();
	
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Integer getTotalAuditCount(Map<String,SearchSortInfoVO> searchParamsInfoMap) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Object> criteriaQuery = criteriaBuilder.createQuery(Object.class);
		Root<Doctor> root = criteriaQuery.from(Doctor.class);
		criteriaQuery = getSearchSortCriteriaQuery(criteriaBuilder, criteriaQuery, root, null, searchParamsInfoMap);
		TypedQuery typedQuery = entityManager.createQuery(criteriaQuery.select(root).distinct(true));
		List<Doctor> doctors = typedQuery.getResultList();
		if (doctors != null) {
			return doctors.size();
		}
		return 0;
	}
	
	
	private <T extends Object> CriteriaQuery<Object> getSearchSortCriteriaQuery(CriteriaBuilder criteriaBuilder,
			CriteriaQuery<Object> criteriaQuery, Root<T> root, Pageable pageRequest,
			Map<String,SearchSortInfoVO> searchParamsInfoMap){
		Collection<Predicate> predicates = new ArrayList<Predicate>();
		String modifiedStartDate = null;
		String modifiedEndDate = null ;
		for(Entry<String,SearchSortInfoVO> entry : searchParamsInfoMap.entrySet()){
			if(entry.getKey().equals(SearchSortInfoVO.MODIFIED_START_DATE)){
				if (entry.getValue().getSearchedValues() != null && entry.getValue().getSearchedValues().size() > 0) {
					modifiedStartDate = (String) entry.getValue().getSearchedValues().get(0);
				}
				continue;
			}
			if(entry.getKey().equals(SearchSortInfoVO.MODIFIED_END_DATE)){
				if (entry.getValue().getSearchedValues() != null && entry.getValue().getSearchedValues().size() > 0) {
					modifiedEndDate = (String) entry.getValue().getSearchedValues().get(0);
				}
				continue;
			}
			
			if (entry.getValue().getSearchedValues() != null && !entry.getValue().getSearchedValues().isEmpty()) {
				Predicate predicate = root.<Object>get(entry.getKey()).in(entry.getValue().getSearchedValues());
				predicates.add(predicate);
			} else if (!StringUtils.isEmpty(entry.getValue().getFilterValue())) {
				String filterValue = getLikeStringQuery(entry.getValue().getFilterValue());
				Predicate predicate = criteriaBuilder.like(root.<String>get(entry.getKey()), filterValue);
				predicates.add(predicate);
			} else{
				continue;
			}

			Predicate predicate = root.<Object>get(entry.getKey()).in(entry.getValue().getSearchedValues());
			predicates.add(predicate);
		}
		if(StringUtils.isNotBlank(modifiedStartDate) && StringUtils.isNotBlank(modifiedEndDate)){
			Date startDate = null;
			Date endDate = null;
			startDate = DateUtil.convertStringToDate(modifiedStartDate, MM_DD_YYYY_HH_MM_SS_DATE_FORMAT);
			endDate = DateUtil.convertStringToDate(modifiedEndDate, MM_DD_YYYY_HH_MM_SS_DATE_FORMAT);
			Predicate predicate = criteriaBuilder.between(
					root.<Date>get("modifiedDate"), startDate, endDate);
			predicates.add(predicate);
		}
		
		// sort data by modified date
				criteriaQuery
						.orderBy(criteriaBuilder.desc(root.<Date>get("modifiedDate")));

		
		if (pageRequest != null) {
			if (pageRequest.getSort() != null) {
				Sort sort = pageRequest.getSort();
				Iterator<Order> orders = sort.iterator();
				while (orders.hasNext()) {
					Order order = orders.next();
					Direction direction = order.getDirection();
					String property = order.getProperty();
					if(property.equals(SearchSortInfoVO.MODIFIED_START_DATE) 
							||property.equals(SearchSortInfoVO.MODIFIED_END_DATE)){
						property = "modifiedDate";
					}
					if(Direction.ASC.equals(direction)){
						criteriaQuery.orderBy(criteriaBuilder
								.asc(root.<Date>get(property)));
					}
					else{
						criteriaQuery.orderBy(criteriaBuilder
								.desc(root.<Date>get(property)));
					}
					break;	
				}
			}
		}
		criteriaQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()])));
		return criteriaQuery;
	}
	
	private String getLikeStringQuery(String filterValue) {

		StringBuilder builder = new StringBuilder();
		if (filterValue.contains("%")) {
			builder.append(filterValue);
		} else {
			builder.append("%").append(filterValue).append("%");
		}

		String result = builder.toString();
		return result;
	}
}
