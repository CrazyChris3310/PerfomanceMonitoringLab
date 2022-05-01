package com.example.JSFLab;

import com.example.JSFLab.mbeans.Counter;
import com.example.JSFLab.mbeans.CounterMBean;
import com.example.JSFLab.mbeans.Square;
import com.example.JSFLab.mbeans.SquareMBean;

import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

//    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private static Scanner scanner = new Scanner(System.in);

    public static double readDouble(String message) {
        while (true) {
            System.out.print("Enter " + message + ": ");
//            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            try {
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid format. Try again.");
            }
        }
    }

    public static void main(String[] args) {
        Square squareBean = new Square();
        Counter counterBean = new Counter();
        try {
            ObjectName objectName = new ObjectName("com.example.JSFLab.mbean:type=basic,name=square");
            ObjectName nameForCounter = new ObjectName("com.example.JSFLab.mbean:type=basic,name=counter");
            MBeanServer server = ManagementFactory.getPlatformMBeanServer();
            server.registerMBean(squareBean, objectName);
            server.registerMBean(counterBean, nameForCounter);
        } catch (MalformedObjectNameException | NotCompliantMBeanException |
                InstanceAlreadyExistsException | MBeanRegistrationException e) {
            e.printStackTrace();
        }

        while (true) {
            double radius = readDouble("radius");
            double x = readDouble("x");
            double y = readDouble("y");

            squareBean.calculateSquare(radius);

            PointData pointData = new PointData();
            pointData.setR(radius);
            pointData.setX(x);
            pointData.setY(y);
            pointData.calculateHit();

            counterBean.addHit(pointData.getMatch().equals("Да"));

            System.out.println(pointData);

        }
    }

}