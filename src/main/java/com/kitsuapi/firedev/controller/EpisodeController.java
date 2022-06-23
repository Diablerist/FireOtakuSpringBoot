package com.kitsuapi.firedev.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kitsuapi.firedev.model.Episode;
import com.kitsuapi.firedev.service.ApiRequests;

@Controller
@RequestMapping("episode")
public class EpisodeController {
    
    @GetMapping("{id}")
    public ModelAndView detailsEpisode(@PathVariable("id") int id) {
        ModelAndView mv = new ModelAndView("episode");
        Episode episode = ApiRequests.episodeById(id);
        mv.addObject("episode", episode.getData());
        return mv;
    }
}
