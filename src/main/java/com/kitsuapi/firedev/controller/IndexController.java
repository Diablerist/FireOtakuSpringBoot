package com.kitsuapi.firedev.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kitsuapi.firedev.model.AnimeList;
import com.kitsuapi.firedev.model.DataResponse;
import com.kitsuapi.firedev.model.MangaList;
import com.kitsuapi.firedev.service.ApiRequests;

@Controller
public class IndexController {

    @RequestMapping("/")
    public ModelAndView trending() {
        ModelAndView mv = new ModelAndView("index");
        AnimeList animes = ApiRequests.trendingAnimes();
        mv.addObject("animes", animes.getData());
        MangaList mangas = ApiRequests.trendingMangas();
        mv.addObject("mangas", mangas.getData());
        DataResponse topAnime = animes.getData().get(0);
        mv.addObject("topanime", topAnime);
        return mv;
    }
}
