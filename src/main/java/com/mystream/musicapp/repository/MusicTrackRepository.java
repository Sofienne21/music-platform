package com.mystream.musicapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mystream.musicapp.model.MusicTrack;

public interface MusicTrackRepository extends JpaRepository<MusicTrack, String>{

}
