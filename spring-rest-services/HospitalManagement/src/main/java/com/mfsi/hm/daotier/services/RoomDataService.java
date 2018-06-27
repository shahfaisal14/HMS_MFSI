/**
 * 
 */
package com.mfsi.hm.daotier.services;

import java.util.Set;

import com.mfsi.hm.daotier.models.Room;

/**
 * @author shah
 *
 */
public interface RoomDataService {
	
	public Room createRoom(Room room);

	public Set<Room> createRooms(Set<Room> rooms);

}
