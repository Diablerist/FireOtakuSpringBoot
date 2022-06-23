package com.kitsuapi.firedev.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kitsuapi.firedev.model.Anime;
import com.kitsuapi.firedev.model.EpisodeList;
import com.kitsuapi.firedev.service.ApiRequests;

@Controller
@RequestMapping("anime")
public class AnimeController {
    
    @GetMapping("{id}/{page}")
    public ModelAndView detailsAnime(@PathVariable("id") int id, @PathVariable("page") int page) {
        ModelAndView mv = new ModelAndView("anime");
        Anime anime = ApiRequests.animeById(id);
        mv.addObject("anime", anime.getData());
        EpisodeList episodes = ApiRequests.episodesByAnime(id, page);
        mv.addObject("episodes", episodes.getData());
        Integer pageNumber = page + 18;
        mv.addObject("page", pageNumber);
        return mv;
    }
}
