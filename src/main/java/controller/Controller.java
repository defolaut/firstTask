package controller;

import network.Network;
import network.Path;
import routeProviders.DomRu;
import routeProviders.RouteNotFoundException;
import routeProviders.RouteProvider;
import routeProviders.Visitor;

import java.io.*;
import java.util.HashMap;

public class Controller {
    public static final String DEFAULT_FILE_NAME = "myNetwork.txt";

    private HashMap<String, RouteProvider> routeProviderHashMap;
    private HashMap<String, Network> networkHashMap;

    private Controller() {
        routeProviderHashMap = new HashMap<>();
        networkHashMap = new HashMap<>();
    }

    private void putNetworkToFile(String fileName) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            Network.writeNetwork(bw, Network.getMyNetwork());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addNetworkFromFile(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            addNetwork(Network.readNetwork(br));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
    }

    public static Controller getControllerWithAllProvidersAndNetworks() {
        Controller controller = new Controller();
        controller.addRouteProvider(new DomRu());
        controller.addNetwork(Network.getTestNetwork());

        controller.putNetworkToFile(Controller.DEFAULT_FILE_NAME);
        controller.addNetworkFromFile(Controller.DEFAULT_FILE_NAME);
        return controller;
    }

    private void addRouteProvider(RouteProvider routeProvider) {
        routeProviderHashMap.put(routeProvider.getProviderName(), routeProvider);
    }

    private void addNetwork(Network network) {
        networkHashMap.put(network.getNetworkName(), network);
    }

    public Path getPathByNetProviderAndTwoID(String net, String provider, int id1, int id2) throws RouteNotFoundException {
        Network network = getNetworkFromString(net);

        if (!routeProviderHashMap.containsKey(provider) || network == null) {
            throw new RouteNotFoundException();
        }

        RouteProvider routeProvider = routeProviderHashMap.get(provider);
        return routeProvider.getRoute(id1, id2, network);
    }

    private Network getNetworkFromString(String net) {
        if (net.contains(".txt")) {
            try (BufferedReader br = new BufferedReader(new FileReader(net))) {
                return Network.readNetwork(br);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassCastException e) {
                e.printStackTrace();
            }
            return null;
        } else {
            return networkHashMap.get(net);
        }
    }

    public void printAllRoutes(String net, String provider, int id1, int id2) throws RouteNotFoundException {
        System.out.println("\nALL ROUTES BETWEEN id1 = " + id1 + " and id2 = " + id2 +
                        " in Network " + net + " with Provider " + provider + ":");

        int i = 1;
        for (Visitor visitor : routeProviderHashMap.get(provider).getAllVisitors(id1, id2, getNetworkFromString(net))) {
            System.out.println("\nRoute " + i + " is:");
            visitor.printVisitor();
            i++;
        }
    }

}
