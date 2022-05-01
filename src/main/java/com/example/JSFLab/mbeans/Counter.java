package com.example.JSFLab.mbeans;

import javax.management.MBeanNotificationInfo;
import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;

public class Counter extends NotificationBroadcasterSupport implements CounterMBean {
  private int allHits = 0;
  private int positiveHits = 0;
  private boolean wasPositive = true;

  private long sequence = 0;

  public void addHit(boolean isPositive) {
    allHits += 1;
    positiveHits += isPositive ? 1 : 0;

    if (!wasPositive && !isPositive) {
      Notification notification = new Notification("someType", this, ++sequence,
                                                   System.currentTimeMillis(),
                                                   "Two negative hits happened");
      sendNotification(notification);
    }

    wasPositive = isPositive;
  }

  @Override
  public int getAllHits() {
    return allHits;
  }

  @Override
  public int getPositiveHits() {
    return positiveHits;
  }

}
