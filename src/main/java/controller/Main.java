package controller;

import network.Network;
import network.Path;
import network.PathElement;
import routeProvider.RouteNotFoundException;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter command, example: route network, provider, id1, id2");
        String inputString = scanner.nextLine();

        Controller controller = Controller.getControllerWithAllProvidersAndNetworks();

        String[] splitInput = inputString.split("\\s");
        try {
            String net = splitInput[1].split(",")[0];
            String provider = splitInput[2].split(",")[0];
            int id1 = Integer.parseInt(splitInput[3].split(",")[0]);
            int id2 = Integer.parseInt(splitInput[4]);

            Path path = controller.getPathByNetProviderAndTwoID(net, provider, id1, id2);
            for (PathElement pathElement : path.getPathElements()) {
                System.out.println(pathElement.getID());
            }
        } catch (RouteNotFoundException e) {
            e.printStackTrace();
        }
    }
}
