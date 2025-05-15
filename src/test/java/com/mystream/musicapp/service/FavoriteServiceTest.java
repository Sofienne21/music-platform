package com.mystream.musicapp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.mystream.musicapp.model.Favorite;
import com.mystream.musicapp.model.MusicTrack;
import com.mystream.musicapp.model.Users;
import com.mystream.musicapp.repository.FavoriteRepository;
import com.mystream.musicapp.repository.MusicTrackRepository;
import com.mystream.musicapp.repository.UsersRepository;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
public class FavoriteServiceTest {

    @Mock
    private FavoriteRepository favoriteRepository;
    @Mock 
    private UsersRepository usersRepository;
    @Mock
    private MusicTrackRepository musicTrackRepository;

    @InjectMocks
    private FavoriteService favoriteService;
    
    @Test
    void shouldAddFavoriteSuccessfully() {

        Users user = Users.builder().id(1L).name("Sofienne").email("sofienne@gmail.com").build();
        MusicTrack track = MusicTrack.builder().id("YT123").title("titre").artist("artiste").source("Youtube").build();
        Favorite savedFavorite = Favorite.builder().user(user).track(track).build();

        when(usersRepository.findById(1L)).thenReturn(Optional.of(user));
        when(musicTrackRepository.findById("YT123")).thenReturn(Optional.of(track));
        when(favoriteRepository.save(any(Favorite.class))).thenReturn(savedFavorite);

        Favorite result = favoriteService.addFavorite(1L, track);
        
        assertNotNull(result);
        assertEquals("YT123", result.getTrack().getId());
        assertEquals("Sofienne", result.getUser().getName());
        verify(favoriteRepository).save(any(Favorite.class));
    }

    @Test
    void shouldThrowWhenUserNotFound() {
        MusicTrack track = MusicTrack.builder()
            .id("YT123")
            .title("Titre")
            .artist("Artiste")
            .source("YOUTUBE")
            .build();

        when(usersRepository.findById(999L)).thenReturn(Optional.empty());

        // Act & Assert
        Exception ex = assertThrows(IllegalArgumentException.class, () ->
            favoriteService.addFavorite(999L, track)
        );

        assertEquals("Utilisateur non trouvable...", ex.getMessage());
    }

    @Test
    void shouldThrowWhenGettingFavoritesWithUnknownUser() {
        when(usersRepository.findById(999L)).thenReturn(Optional.empty());

        Exception ex = assertThrows(RuntimeException.class, () ->
            favoriteService.getFavoritesByUser(999L)
        );

        assertEquals("User not found", ex.getMessage());
    }

    @Test
    void shouldDeleteFavoriteSuccessfully() {
        Long favoriteId = 42L;

        favoriteService.deleteFavorite(favoriteId);

        verify(favoriteRepository).deleteById(favoriteId);
    }


}
