package com.kitsuapi.firedev.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.kitsuapi.firedev.client.ApiRequestClient;
import com.kitsuapi.firedev.model.Anime;
import com.kitsuapi.firedev.model.EpisodeList;
import com.kitsuapi.firedev.model.Pagination;
import com.kitsuapi.firedev.service.AnimeSearchService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("anime")
public class AnimeController {

    final AnimeSearchService animeSearchService;
    
    public AnimeController(AnimeSearchService animeSearchService) {
        this.animeSearchService = animeSearchService;
    }

    @GetMapping("{id}/{page}")
    public ModelAndView detailsAnime(@PathVariable("id") String id, @PathVariable("page") Integer page) {
        ModelAndView mv = new ModelAndView("anime");
        String pageString = page.toString();
        Mono<Anime> anime = ApiRequestClient.findAnimeById(id);
        mv.addObject("anime", anime.block().getData());
        Mono<EpisodeList> episodes = ApiRequestClient.findEpisodesByAnime(id, pageString);
        mv.addObject("episodes", episodes.block().getData());
        Pagination pagination = new Pagination(page);
        mv.addObject("pagination", pagination);

        animeSearchService.saveAnimeSearch(id, anime.block().getData().getAttributes().getSlug());

        return mv;
    }
}
