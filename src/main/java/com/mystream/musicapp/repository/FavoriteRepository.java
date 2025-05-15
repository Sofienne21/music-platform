package com.mystream.musicapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mystream.musicapp.model.Favorite;
import com.mystream.musicapp.model.Users;

public interface FavoriteRepository extends JpaRepository<Favorite, Long>{
    List<Favorite> findByUser(Users user);
}
