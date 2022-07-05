package com.kitsuapi.firedev.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.kitsuapi.firedev.model.MangaSearches;
import com.kitsuapi.firedev.repository.MangaSearchRepository;

@Service
public class MangaSearchService {

    final MangaSearchRepository mangaSearchRepository;
    
    public MangaSearchService(MangaSearchRepository mangaSearchRepository) {
        this.mangaSearchRepository = mangaSearchRepository;
    }

    /**
     * Salva um manga no banco de dados.
     * @param id Recebe o ID do manga a ser salvo.
     * @param slug Recebe o slug do manga a ser salvo.
     */
    @Transactional
    public void saveMangaSearch(String id, String slug) {

        if (mangaSearchRepository.existsByMangaId(id)) {
            MangaSearches manga = mangaSearchRepository.findByMangaId(id);
            manga.setLastSearch(LocalDateTime.now(ZoneId.of("UTC")));
            manga.setSearchCount(manga.getSearchCount() + 1);
            mangaSearchRepository.save(manga);
        } else {
            MangaSearches manga = new MangaSearches();
            manga.setMangaId(id);
            manga.setSlug(slug);
            manga.setLastSearch(LocalDateTime.now(ZoneId.of("UTC")));
            manga.setSearchCount(1);
            mangaSearchRepository.save(manga);
        }
    }

    /**
     * Busca se um manga já existe no banco de dados.
     * @param mangaId Recebe o ID do manga buscado.
     * @return Retorna um boolean referente a existência de um manga específico no banco.
     */
    public boolean existsByMangaId(String mangaId) {
        return mangaSearchRepository.existsByMangaId(mangaId);
    }

    /**
     * Busca todos os mangas no banco de dados.
     */
    public List<MangaSearches> findAll() {
        return mangaSearchRepository.findAll(Sort.by(Sort.Direction.DESC, "searchCount"));
    }

}
