package com.kitsuapi.firedev.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.kitsuapi.firedev.model.Manga;
import com.kitsuapi.firedev.service.ApiRequests;

@Controller
@RequestMapping("manga")
public class MangaController {
    
    @GetMapping("{id}")
    public ModelAndView detailsManga(@PathVariable("id") int id) {
        ModelAndView mv = new ModelAndView("manga");
        Manga manga = ApiRequests.mangaById(id);
        mv.addObject("manga", manga.getData());
        return mv;
    }
}
