package com.kitsuapi.firedev.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.kitsuapi.firedev.client.ApiRequestClient;
import com.kitsuapi.firedev.model.AnimeList;
import com.kitsuapi.firedev.model.Categories;
import com.kitsuapi.firedev.model.DataResponse;
import com.kitsuapi.firedev.model.MangaList;

import reactor.core.publisher.Mono;

@RestController
public class IndexController {

    @RequestMapping("/")
    public ModelAndView trending() {
        ModelAndView mv = new ModelAndView("index");
        Mono<AnimeList> animes = ApiRequestClient.trendingAnime();
        mv.addObject("animes", animes.block().getData());
        Mono<MangaList> mangas = ApiRequestClient.trendingManga();
        mv.addObject("mangas", mangas.block().getData());
        DataResponse topAnime = animes.block().getData().get(0);
        mv.addObject("topanime", topAnime);
        Mono<Categories> categories = ApiRequestClient.categories();
        mv.addObject("categories", categories.block().getData());

        List<Integer> list = new ArrayList<Integer>();

        for (int counter = 1907; counter < 2022 ; counter++) {
            list.add(counter);
        }
        mv.addObject("year", list);

        return mv;
    }
}
