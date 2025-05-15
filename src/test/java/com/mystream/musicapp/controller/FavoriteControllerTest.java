package com.mystream.musicapp.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mystream.musicapp.model.MusicTrack;
import com.mystream.musicapp.model.Users;
import com.mystream.musicapp.repository.UsersRepository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.http.MediaType;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
public class FavoriteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    ObjectMapper objectMapper;

    private Long userId;

    @BeforeEach
    void setUp() {
        Users user = Users.builder()
                .name("Sofienne")
                .email("sofienne@gmail.com")
                .build();
        userId = usersRepository.save(user).getId();
    }

    @Test
    void shouldAddFavoriteSuccessfully() throws Exception {
        MusicTrack track = MusicTrack.builder()
            .id("YT123")
            .title("Test song")
            .artist("Test artist")
            .source("YOUTUBE")
            .build();
            
        mockMvc.perform(post("/api/favorites/"+userId)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(track)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.track.id").value("YT123"))
                .andExpect(jsonPath("$.user.id").value(userId.intValue()));
    }

    @Test
    void shouldReturnFavoritesForUser() throws Exception {
        // 1. Créer un utilisateur
        Users user = Users.builder()
            .name("Sofienne")
            .email("sofienne@example.com")
            .build();
        user = usersRepository.save(user);

        // 2. Créer un morceau
        MusicTrack track = MusicTrack.builder()
            .id("YT123")
            .title("Test Song")
            .artist("Test Artist")
            .source("YOUTUBE")
            .build();

        // 3. Appeler l’API pour l’ajouter comme favori
        mockMvc.perform(post("/api/favorites/" + user.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(track)))
            .andExpect(status().isOk());

        // 4. Appeler l’API GET pour récupérer les favoris
        mockMvc.perform(
                org.springframework.test.web.servlet.request.MockMvcRequestBuilders
                    .get("/api/favorites/" + user.getId()))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].track.id").value("YT123"))
            .andExpect(jsonPath("$[0].user.id").value(user.getId().intValue()));
    }


}
