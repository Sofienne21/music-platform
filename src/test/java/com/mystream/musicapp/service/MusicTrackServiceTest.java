package com.mystream.musicapp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.mystream.musicapp.model.MusicTrack;
import com.mystream.musicapp.repository.MusicTrackRepository;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
public class MusicTrackServiceTest {

    @Mock
    MusicTrackRepository musicTrackRepository;

    @InjectMocks
    MusicTrackService musicTrackService;

    @Test
    void shouldSaveTrackSuccessfully() {
        MusicTrack track = MusicTrack.builder().id("YT123").title("titre").artist("artiste").source("Youtube").build();
        when(musicTrackRepository.save(any(MusicTrack.class))).thenReturn(track);
        MusicTrack result = musicTrackRepository.save(track);
        verify(musicTrackRepository).save(any(MusicTrack.class));
        assertEquals("YT123", result.getId());
        assertEquals("titre", result.getTitle());
    }

    @Test
    void shouldReturnTrackWhenFoundById() {
    
        MusicTrack track = MusicTrack.builder()
            .id("YT123")
            .title("titre")
            .artist("artiste")
            .source("Youtube")
            .build();

        when(musicTrackRepository.findById("YT123")).thenReturn(Optional.of(track));

        // Act
        MusicTrack result = musicTrackService.getTrackById("YT123");

        // Assert
        assertNotNull(result);
        assertEquals("YT123", result.getId());
        assertEquals("titre", result.getTitle());
    }

}
