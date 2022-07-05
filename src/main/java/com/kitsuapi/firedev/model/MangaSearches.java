package com.kitsuapi.firedev.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "MANGA_SEARCHES")
@Data
public class MangaSearches implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, unique = true, length = 10)
    private String mangaId;

    @Column(nullable = false, unique = true, length = 255)
    private String slug;

    @Column(nullable = false, unique = false, length = 10)
    private Integer searchCount;

    @Column(nullable = false)
    private LocalDateTime lastSearch;
}
