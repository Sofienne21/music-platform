package com.mystream.musicapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mystream.musicapp.model.Users;

public interface UsersRepository extends JpaRepository<Users, Long>{

}
