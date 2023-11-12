package com.chunjae.pro05.persis;

import com.chunjae.pro05.entity.Keyword;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface KeywordMapper {
    List<Keyword> keywordList();

    int keywordInsert(Keyword keyword);

    List<Keyword> getKeywordsByUid(String uid);
}
