package com.kitsuapi.firedev.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.kitsuapi.firedev.model.Anime;
import com.kitsuapi.firedev.model.AnimeList;
import com.kitsuapi.firedev.model.Episode;
import com.kitsuapi.firedev.model.EpisodeList;
import com.kitsuapi.firedev.model.Manga;
import com.kitsuapi.firedev.model.MangaList;

@Service
public class ApiRequests {

    private static final String BASE_URL = "kitsu.io/api/edge";
    private static final String ANIME = "anime";
    private static final String EPISODES = "episodes";
    private static final String MANGA = "manga";
    private static final HttpEntity<String> HTTP_REQUEST_ENTITY;

    static {
        final HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/vnd.api+json");
        HTTP_REQUEST_ENTITY = new HttpEntity<>(headers);
    }

    private static String urlBuild(String path) {
        UriComponents url = UriComponentsBuilder.newInstance()
            .scheme("https")
            .host(BASE_URL)
            .path(path)
            .build();
        return url.toString();
    }

    public static Anime animeById(int id) {
        String url = urlBuild(ANIME + "/" + id);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Anime> anime = restTemplate.exchange
            (url, 
            HttpMethod.GET, HTTP_REQUEST_ENTITY, 
            Anime.class);
        return anime.getBody();
    }

    public static Manga mangaById(int id) {
        String url = urlBuild(MANGA + "/" + id);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Manga> manga = restTemplate.exchange
            (url, 
            HttpMethod.GET, HTTP_REQUEST_ENTITY, 
            Manga.class);
        return manga.getBody();
    }

    public static Episode episodeById(int id) {
        String url = urlBuild(EPISODES + "/" + id);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Episode> episode = restTemplate.exchange
            (url, 
            HttpMethod.GET, HTTP_REQUEST_ENTITY, 
            Episode.class);
        return episode.getBody();
    }

    public static EpisodeList episodesByAnime(int id, Integer pagination) {
        String url = urlBuild(ANIME + "/" + id + "/" + EPISODES + "?page%5Blimit%5D={limit}&page%5Boffset%5D={page}");
        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("limit", "18");
        uriVariables.put("page", pagination.toString());
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<EpisodeList> episodes = restTemplate.exchange
            (url, 
            HttpMethod.GET, HTTP_REQUEST_ENTITY, 
            EpisodeList.class,
            uriVariables);
        return episodes.getBody();
    }

    public static AnimeList trendingAnimes() {
        String url = urlBuild("/trending/anime");
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<AnimeList> entity = restTemplate.exchange
            (url, 
            HttpMethod.GET, HTTP_REQUEST_ENTITY, 
            AnimeList.class);
        AnimeList list = entity.getBody();
        return list;
    }

    public static MangaList trendingMangas() {
        String url = urlBuild("/trending/manga");
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<MangaList> entity = restTemplate.exchange
            (url, 
            HttpMethod.GET, HTTP_REQUEST_ENTITY, 
            MangaList.class);
        MangaList list = entity.getBody();
        return list;
    }
}
