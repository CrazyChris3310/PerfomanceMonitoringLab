package com.example.JSFLab.test;

import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;
import java.util.Scanner;

public class Server {

  public static void main(String[] args) {
    Game game = new Game();
    try {
      ObjectName objectName = new ObjectName("com.example.JSFLab.test:type=basic,name=game");
      MBeanServer server = ManagementFactory.getPlatformMBeanServer();
      server.registerMBean(game, objectName);
    } catch (MalformedObjectNameException | NotCompliantMBeanException |
            InstanceAlreadyExistsException | MBeanRegistrationException e) {
      e.printStackTrace();
    }

    Scanner sc = new Scanner(System.in);

    while (true) {
      String a = sc.nextLine();
      String b = sc.nextLine();
      game.setPlayerName(a);
      game.playFootball(b);
    }
  }

}
