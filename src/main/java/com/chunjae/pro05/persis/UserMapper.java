package com.chunjae.pro05.persis;

import com.chunjae.pro05.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface UserMapper {
    User findUserByLoginId(@Param("loginId") String loginId);
    int setUserInfo(@Param("param") User param);

    List<User> findAllUser();
}
