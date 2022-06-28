package com.kitsuapi.firedev.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.kitsuapi.firedev.client.ApiRequestClient;
import com.kitsuapi.firedev.model.Anime;
import com.kitsuapi.firedev.model.EpisodeList;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
@RequestMapping("anime")
public class AnimeController {

    ApiRequestClient apiRequestClient;
    
    @GetMapping("{id}/{page}")
    public ModelAndView detailsAnime(@PathVariable("id") String id, @PathVariable("page") Integer page) {
        ModelAndView mv = new ModelAndView("anime");
        String pageString = page.toString();
        Mono<Anime> anime = apiRequestClient.findAnimeById(id);
        mv.addObject("anime", anime.block().getData());
        Mono<EpisodeList> episodes = apiRequestClient.findEpisodesByAnime(id, pageString);
        mv.addObject("episodes", episodes.block().getData());
        Integer nextPage = page + 18;
        mv.addObject("nextPage", nextPage);
        Integer previousPage = page - 18;
        mv.addObject("prevPage", previousPage);
        Integer firstPage = 0;
        mv.addObject("firstPage", firstPage);

        return mv;
    }
}
