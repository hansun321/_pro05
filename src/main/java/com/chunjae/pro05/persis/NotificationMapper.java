package com.chunjae.pro05.persis;

import com.chunjae.pro05.entity.Keyword;
import com.chunjae.pro05.entity.Notification;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface NotificationMapper {
    List<Notification> notificationList();

    int notificationInsert(Notification notification);

    List<Notification> getNotificationsByUid(String uid);
}
