package applicationFacade;

import controller.Controller;
import network.Path;
import elements.PathElement;
import routeProviders.RouteNotFoundException;

import java.util.Scanner;

public class ApplicationFacade {

    public static void startConsoleApplication(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter command, example: route test, DomRu, id1, id2");
        System.out.println("Also you can use: route myNetwork.txt, DomRu, id1, id2");
        String inputString = scanner.nextLine();

        Controller controller = Controller.getControllerWithAllProvidersAndNetworks();

        String[] splitInput = inputString.split("\\s");
        try {
            String net = splitInput[1].split(",")[0];
            String provider = splitInput[2].split(",")[0];
            int id1 = Integer.parseInt(splitInput[3].split(",")[0]);
            int id2 = Integer.parseInt(splitInput[4]);

            Path path = controller.getPathByNetProviderAndTwoID(net, provider, id1, id2);

            System.out.println("Your path (min count elements) is:");
            for (PathElement pathElement : path.getPathElements()) {
                System.out.println(pathElement.getInfo() + " " + pathElement.getID());
            }

            System.out.println("\n========================================\n");
            System.out.println("Wanna see all possible routes between your ids? [y/n]");
            if (scanner.next().equals("y")) {
                controller.printAllRoutes(net, provider, id1, id2);
            }
        } catch (RouteNotFoundException e) {
            e.printStackTrace();
        } catch (ClassCastException e) {
            e.printStackTrace();
        }

    }
    
}
