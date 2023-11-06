package com.chunjae.pro05.entity;

import lombok.Data;

@Data
public class Board {
    private int bno;
    private String title;
    private String content;
    private String resdate;
    private int visited;
    private String id;
    private int commentCnt;
}
