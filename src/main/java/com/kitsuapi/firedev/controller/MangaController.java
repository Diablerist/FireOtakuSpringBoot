package com.kitsuapi.firedev.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.kitsuapi.firedev.client.ApiRequestClient;
import com.kitsuapi.firedev.model.Manga;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("manga")
public class MangaController {
    
    @GetMapping("{id}")
    public ModelAndView detailsManga(@PathVariable("id") String id) {
        ModelAndView mv = new ModelAndView("manga");
        Mono<Manga> manga = ApiRequestClient.findMangaById(id);
        mv.addObject("manga", manga.block().getData());
        return mv;
    }
}
