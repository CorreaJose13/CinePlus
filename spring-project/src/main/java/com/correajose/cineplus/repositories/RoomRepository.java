package com.correajose.cineplus.repositories;

import com.correajose.cineplus.models.room.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Integer> {
}
