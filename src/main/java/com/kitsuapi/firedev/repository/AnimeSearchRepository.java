package com.kitsuapi.firedev.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kitsuapi.firedev.model.AnimeSearches;

@Repository
public interface AnimeSearchRepository extends JpaRepository<AnimeSearches, UUID> {
    
    boolean existsByAnimeId(String animeId);

    AnimeSearches findByAnimeId(String id);

}
