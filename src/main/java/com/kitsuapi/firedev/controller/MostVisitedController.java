package com.kitsuapi.firedev.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.kitsuapi.firedev.client.ApiRequestClient;
import com.kitsuapi.firedev.model.Anime;
import com.kitsuapi.firedev.model.AnimeSearches;
import com.kitsuapi.firedev.model.DataResponse;
import com.kitsuapi.firedev.model.Manga;
import com.kitsuapi.firedev.model.MangaSearches;
import com.kitsuapi.firedev.service.AnimeSearchService;
import com.kitsuapi.firedev.service.MangaSearchService;

import reactor.core.publisher.Mono;

@RestController
public class MostVisitedController {

    final AnimeSearchService animeSearchService;
    
    public MostVisitedController(AnimeSearchService animeSearchService, MangaSearchService mangaSearchService) {
        this.animeSearchService = animeSearchService;
        this.mangaSearchService = mangaSearchService;
    }

    final MangaSearchService mangaSearchService;

    @GetMapping("mostvisited")
    public ModelAndView mostVisited() {
        List<AnimeSearches> listAnime = animeSearchService.findAll();
        ArrayList<DataResponse> animes = new ArrayList<DataResponse>();
        for (AnimeSearches anime : listAnime) {
            Mono<Anime> a = ApiRequestClient.findAnimeById(anime.getAnimeId());
            animes.add(a.block().getData());
        }
        List<MangaSearches> listManga = mangaSearchService.findAll();
        ArrayList<DataResponse> mangas = new ArrayList<DataResponse>();
        for (MangaSearches manga : listManga) {
            Mono<Manga> a = ApiRequestClient.findMangaById(manga.getMangaId());
            mangas.add(a.block().getData());
        }

        ModelAndView mv = new ModelAndView("mostvisited");
        mv.addObject("animes", animes);
        mv.addObject("mangas", mangas);

        return mv;
    }
}
