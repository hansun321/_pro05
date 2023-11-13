package com.chunjae.pro05.biz;

import com.chunjae.pro05.entity.Notification;
import com.chunjae.pro05.persis.KeywordMapper;
import com.chunjae.pro05.persis.NotificationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationMapper notificationMapper;


    @Override
    public List<Notification> notificationList() {
        return notificationMapper.notificationList();
    }

    @Override
    public int notificationInsert(Notification notification) {
        return notificationMapper.notificationInsert(notification);
    }

    @Override
    public List<Notification> getNotificationsByUid(String uid) {
        return notificationMapper.getNotificationsByUid(uid);
    }
}
