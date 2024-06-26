package com.swyp.boardpick.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.swyp.boardpick.utils.BoardGameEntityListener;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@EntityListeners(BoardGameEntityListener.class) // youtube link embed 형식으로 formatting 해서 저장하기 위해
public class BoardGame {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String thumbnailUrl;
    private String imageUrl;
    private String name;
    private String description;
    private double rating;
    private int ratingCount;
    private int minPlayers;
    private int maxPlayers;
    private int playtime;
    private int ageLimit;
    private double difficulty;
    private String rule;
    private String extraVideo;
    private int likes;
    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "boardGame")
    List<BoardGameCategory> boardGameCategories = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "boardGame")
    List<UserBoardGame> userBoardGames = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "boardGame")
    List<BoardGameTag> boardGameTags = new ArrayList<>();

    public void increaseLikes() {
        likes++;
    }

    public void decreaseLikes() {
        if (likes < 1)
            return ;
        likes--;
    }
}
