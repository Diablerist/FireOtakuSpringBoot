package com.kitsuapi.firedev.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.kitsuapi.firedev.client.ApiRequestClient;
import com.kitsuapi.firedev.model.Episode;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("episode")
public class EpisodeController {
    
    @GetMapping("{id}")
    public ModelAndView detailsEpisode(@PathVariable("id") String id) {
        ModelAndView mv = new ModelAndView("episode");
        Mono<Episode> episode = ApiRequestClient.findEpisodeById(id);
        mv.addObject("episode", episode.block().getData());
        return mv;
    }
}
