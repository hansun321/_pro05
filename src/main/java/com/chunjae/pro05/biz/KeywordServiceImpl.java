package com.chunjae.pro05.biz;

import com.chunjae.pro05.entity.Keyword;
import com.chunjae.pro05.persis.KeywordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KeywordServiceImpl implements KeywordService {

    @Autowired
    private KeywordMapper keywordMapper;

    @Override
    public List<Keyword> getKeywordList() {
        return keywordMapper.keywordList();
    }

    @Override
    public int keywordInsert(Keyword keyword) {
        return keywordMapper.keywordInsert(keyword);
    }

    @Override
    public List<Keyword> getKeywordsByUid(String uid) {
        return keywordMapper.getKeywordsByUid(uid);
    }
}
