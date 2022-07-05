package com.kitsuapi.firedev.client;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.kitsuapi.firedev.model.Anime;
import com.kitsuapi.firedev.model.AnimeList;
import com.kitsuapi.firedev.model.Categories;
import com.kitsuapi.firedev.model.Episode;
import com.kitsuapi.firedev.model.EpisodeList;
import com.kitsuapi.firedev.model.Manga;
import com.kitsuapi.firedev.model.MangaList;

import reactor.core.publisher.Mono;

@Service
public class ApiRequestClient {
    
    
    private static WebClient client;

    /**
     * Gera instancia do WebClient.
     * @param builder
     */
    public ApiRequestClient(WebClient.Builder builder) {
        client = builder.baseUrl("https://kitsu.io/api/edge").build();
    }

    /**
     * Consulta API buscando um anime específico de acordo com ID.
     * @param id Recebe o ID referente ao anime desejado.
     * @return Retorna informações do anime buscado.
     */
    public static Mono<Anime> findAnimeById(String id) {
        return client.get().uri("/anime/" + id)
        .header("Accept", "application/vnd.api+json")
        .retrieve().bodyToMono(Anime.class);
    }

    /**
     * Consulta API buscando um manga específico de acordo com o ID.
     * @param id Recebe o ID referente ao manga desejado.
     * @return Retorna informações do mangá buscado.
     */
    public static Mono<Manga> findMangaById(String id) {
        return client.get().uri("/manga/" + id)
        .header("Accept", "application/vnd.api+json")
        .retrieve().bodyToMono(Manga.class);
    }

    /**
     * Consulta API buscando um episódio específico de acordo com o ID.
     * @param id Recebe o ID referente ao episódio desejado.
     * @return Retorna informações do episódio.
     */
    public static Mono<Episode> findEpisodeById(String id) {
        return client.get().uri("/episodes/" + id)
        .header("Accept", "application/vnd.api+json")
        .retrieve().bodyToMono(Episode.class);
    }

    /**
     * Busca todos os episódios referentes ao ID do anime enviado.
     * @param id Recebe ID do anime.
     * @param page Recebe o numero referente a paginação.
     * @return Retorna todos os episódios ligados ao anime buscado.
     */
    public static Mono<EpisodeList> findEpisodesByAnime(String id, String page) {
        return client.get().uri("/anime/" + id + "/episodes?page[limit]=18&page[offset]=" + page)
            .header("Accept", "application/vnd.api+json")
            .retrieve()
            .bodyToMono(EpisodeList.class);
    }

    /**
     * Busca os mangás referentes aos filtros inseridos.
     * @param text Recebe o filtro de texto que é digitado pelo usuário.
     * @param category Recebe o filtro de categoria selecionado.
     * @return Retorna lista de mangás referentes aos filtros inseridos.
     */
    public static Mono<MangaList> searchManga(String text, String category) {
        String url = "/manga?";

        if (!text.equals("")) {
            url = url + "filter[text]=" + text;
        }
        if (!category.equals("all")) {
            url = url + "&filter[categories]=" + category;
        }

        return client.get().uri(url + "&page[limit]=18&page[offset]=0")
        .header("Accept", "application/vnd.api+json")
        .retrieve().bodyToMono(MangaList.class);
    }

    /**
     * Busca os animes referentes aos filtros inseridos.
     * @param text Recebe o filtro de texto que é digitado pelo usuário.
     * @param category Recebe o filtro de categoria selecionado.
     * @param year Recebe o filtro de ano de lançamento selecionado.
     * @return Retorna lista de animes referentes aos filtros inseridos.
     */
    public static Mono<AnimeList> searchAnime(String text, String category, String year) {
        String url = "/anime?";

        if (!text.equals("")) {
            url = url + "filter[text]=" + text;
        }
        if (!category.equals("all")) {
            url = url + "&filter[categories]=" + category;
        }
        if (!year.equals("all")) {
            url = url + "&filter[seasonYear]=" + year;
        }

        return client.get().uri(url + "&page[limit]=18&page[offset]=0")
        .header("Accept", "application/vnd.api+json")
        .retrieve().bodyToMono(AnimeList.class);
    }

    /**
     * Busca os animes mais populares.
     * @return Retorna lista de animes mais populares.
     */
    public static Mono<AnimeList> trendingAnime() {
        return client.get().uri("/trending/anime")
        .header("Accept", "application/vnd.api+json")
        .retrieve().bodyToMono(AnimeList.class);
    }

    /**
     * Busca os mangás mais populares.
     * @return Retorna lista de mangás mais populares.
     */
    public static Mono<MangaList> trendingManga() {
        return client.get().uri("/trending/manga")
        .header("Accept", "application/vnd.api+json")
        .retrieve().bodyToMono(MangaList.class);
    }

    /**
     * Busca todas as categorias disponíveis.
     * @return Retorna lista de categorias em ordem alfabética.
     */
    public static Mono<Categories> categories() {
        return client.get().uri("/categories?page[limit]=200&page[offset]=0&sort=title")
        .header("Accept", "application/vnd.api+json")
        .retrieve().bodyToMono(Categories.class);
    }
}
