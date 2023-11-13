package com.chunjae.pro05.biz;

import com.chunjae.pro05.entity.Notification;

import java.util.List;

public interface NotificationService {
    public List<Notification> notificationList();

    public int notificationInsert(Notification notification);

    public List<Notification> getNotificationsByUid(String uid);
}
