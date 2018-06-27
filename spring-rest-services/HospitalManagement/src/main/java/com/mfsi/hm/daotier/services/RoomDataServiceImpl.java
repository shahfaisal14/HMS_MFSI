/**
 * 
 */
package com.mfsi.hm.daotier.services;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mfsi.hm.daotier.models.Room;
import com.mfsi.hm.daotier.repositories.RoomRepository;

/**
 * @author shah
 *
 */
@Service("roomDataService")
public class RoomDataServiceImpl implements RoomDataService {

	@Autowired
	private RoomRepository roomRepository;
	
	@Override
	public Room createRoom(Room room) {
		Room newRoom = null;
		if(room != null){
			newRoom = roomRepository.save(room);
		}
		return newRoom;
	}
	
	@Override
	public Set<Room> createRooms(Set<Room> rooms){
		Set<Room> newRooms = null;
		if(rooms != null){
			newRooms = roomRepository.saveAll(rooms).stream().collect(Collectors.toSet());
		}
		return newRooms;
	}

}
