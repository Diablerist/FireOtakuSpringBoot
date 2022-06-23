package com.kitsuapi.firedev.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Attributes{
    private Date createdAt;
    private Date updatedAt;
    private String slug;
    private String synopsis;
    private String description;
    private int coverImageTopOffset;
    private Titles titles;
    private String canonicalTitle;
    private ArrayList<String> abbreviatedTitles;
    private String averageRating;
    private Map<Integer, Integer> ratingFrequencies;
    private int userCount;
    private int favoritesCount;
    private String startDate;
    private Object endDate;
    private Date nextRelease;
    private int popularityRank;
    private int ratingRank;
    private String ageRating;
    private String ageRatingGuide;
    private String subtype;
    private String status;
    private Object tba;
    private Image posterImage;
    private Image coverImage;
    private String showType;
    private boolean nsfw;

    private int chapterCount;
    private int volumeCount;
    private String serialization;
    private String mangaType;

    private Object episodeCount;
    private int episodeLength;
    private int totalLength;
    private String youtubeVideoId;

    private int seasonNumber;
    private int number;
    private int relativeNumber;
    private Thumbnail thumbnail;
    private String airdate;
}
