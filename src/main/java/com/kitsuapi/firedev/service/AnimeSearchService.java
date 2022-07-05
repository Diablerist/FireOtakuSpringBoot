package com.kitsuapi.firedev.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.kitsuapi.firedev.model.AnimeSearches;
import com.kitsuapi.firedev.repository.AnimeSearchRepository;


@Service
public class AnimeSearchService {

    final AnimeSearchRepository animeSearchRepository;
    
    public AnimeSearchService(AnimeSearchRepository animeSearchRepository) {
        this.animeSearchRepository = animeSearchRepository;
    }

    /**
     * Salva um anime no banco de dados.
     * @param id Recebe o ID do anime a ser salvo.
     * @param slug Recebe o slug do anime a ser salvo.
     */
    @Transactional
    public void saveAnimeSearch(String id, String slug) {

        if (animeSearchRepository.existsByAnimeId(id)) {
            AnimeSearches anime = animeSearchRepository.findByAnimeId(id);
            anime.setLastSearch(LocalDateTime.now(ZoneId.of("UTC")));
            anime.setSearchCount(anime.getSearchCount() + 1);
            animeSearchRepository.save(anime);
        } else {
            AnimeSearches anime = new AnimeSearches();
            anime.setAnimeId(id);
            anime.setSlug(slug);
            anime.setLastSearch(LocalDateTime.now(ZoneId.of("UTC")));
            anime.setSearchCount(1);
            animeSearchRepository.save(anime);
        }
    }

    /**
     * Busca se um anime já existe no banco de dados.
     * @param animeId Recebe o ID do anime buscado.
     * @return Retorna boolean referente a existência de um anime específico no banco.
     */
    public boolean existsByAnimeId(String animeId) {
        return animeSearchRepository.existsByAnimeId(animeId);
    }

    /**
     * Busca todos os animes no banco de dados.
     */
    public List<AnimeSearches> findAll() {
        return animeSearchRepository.findAll();
    }
    
}
