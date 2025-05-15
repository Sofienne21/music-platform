package com.mystream.musicapp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mystream.musicapp.model.MusicTrack;
import com.mystream.musicapp.service.MusicTrackService;

import lombok.RequiredArgsConstructor;

import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/tracks")
@RequiredArgsConstructor
public class MusicTrackController {

    private final MusicTrackService musicTrackService;

    @PostMapping
    public MusicTrack saveTrack(@RequestBody MusicTrack track) {
        return musicTrackService.saveTrack(track);
    }
    
    @GetMapping("/{id}")
    public MusicTrack getTrackById(@PathVariable String id) {
        return musicTrackService.getTrackById(id);
    }
}
