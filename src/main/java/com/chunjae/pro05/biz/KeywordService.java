package com.chunjae.pro05.biz;

import com.chunjae.pro05.entity.Keyword;

import java.util.List;

public interface KeywordService {
    public List<Keyword> getKeywordList();

    public int keywordInsert(Keyword keyword);

    public List<Keyword> getKeywordsByUid(String uid);
}
