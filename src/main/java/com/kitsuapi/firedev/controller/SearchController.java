package com.kitsuapi.firedev.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.kitsuapi.firedev.client.ApiRequestClient;
import com.kitsuapi.firedev.model.AnimeList;
import com.kitsuapi.firedev.model.Categories;
import com.kitsuapi.firedev.model.MangaList;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("search")
public class SearchController {

    @GetMapping
    public ModelAndView search(@RequestParam("text") String text
    ,@RequestParam("category") String category
    ,@RequestParam("year") String year) {

        ModelAndView mv = new ModelAndView("search");
        Mono<MangaList> mangaList = ApiRequestClient.searchManga(text, category);
        mv.addObject("mangaList", mangaList.block().getData());
        Mono<AnimeList> animeList = ApiRequestClient.searchAnime(text, category, year);
        mv.addObject("animeList", animeList.block().getData());
        Mono<AnimeList> trendingAnime = ApiRequestClient.trendingAnime();
        mv.addObject("topanime", trendingAnime.block().getData().get(0));
        Mono<Categories> categories = ApiRequestClient.categories();
        mv.addObject("categories", categories.block().getData());

        List<Integer> list = new ArrayList<Integer>();

        for (int counter = 2022; counter > 1907 ; counter--) {
            list.add(counter);
        }
        mv.addObject("year", list);

        return mv;
    }
}
