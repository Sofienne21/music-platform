package com.mystream.musicapp.service;

import org.springframework.stereotype.Service;

import com.mystream.musicapp.model.MusicTrack;
import com.mystream.musicapp.repository.MusicTrackRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MusicTrackService {

    private final MusicTrackRepository musicTrackRepository;

    public MusicTrack saveTrack(MusicTrack track){
        return musicTrackRepository.save(track);
    }

    public MusicTrack getTrackById(String id) {
        return musicTrackRepository.findById(id).orElse(null);
    }
}
