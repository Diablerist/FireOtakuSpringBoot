package com.kitsuapi.firedev.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kitsuapi.firedev.model.MangaSearches;

@Repository
public interface MangaSearchRepository extends JpaRepository<MangaSearches, UUID> {
    
    boolean existsByMangaId(String animeId);

    MangaSearches findByMangaId(String id);

}
