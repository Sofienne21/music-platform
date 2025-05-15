package com.mystream.musicapp.service;


import com.mystream.musicapp.model.Favorite;
import com.mystream.musicapp.model.MusicTrack;
import com.mystream.musicapp.model.Users;
import com.mystream.musicapp.repository.FavoriteRepository;
import com.mystream.musicapp.repository.MusicTrackRepository;
import com.mystream.musicapp.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FavoriteService {

    private final FavoriteRepository favoriteRepository;
    private final MusicTrackRepository musicTrackRepository;
    private final UsersRepository usersRepository;

    public Favorite addFavorite(Long userId, MusicTrack track) {
        Users user = usersRepository.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("Utilisateur non trouvable..."));

        MusicTrack existingTrack = musicTrackRepository.findById(track.getId())
                    .orElseGet(() -> musicTrackRepository.save(track));

        Favorite favorite = Favorite.builder()
            .user(user)
            .track(existingTrack)
            .build();

        Favorite saved = favoriteRepository.save(favorite);

        if(saved == null) {
            throw new IllegalStateException("Erreur lors de la sauvgarde du favori");
        }

        return saved;
    }

    public List<Favorite> getFavoritesByUser(Long userId) {
        Users user = usersRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));

        return favoriteRepository.findByUser(user);
    }

    public void deleteFavorite(Long favoriteId) {
        favoriteRepository.deleteById(favoriteId);
    }

}
