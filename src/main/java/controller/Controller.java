package controller;

import network.MyNetworkBuilder;
import network.Network;
import network.Path;
import network.TestNetworkBuilder;
import networkIO.NetworkJsonSaver;
import networkIO.NetworkObjectSaver;
import routeProviders.DomRu;
import routeProviders.RouteNotFoundException;
import routeProviders.RouteProvider;
import routeProviders.Visitor;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Controller {
    public static final String MY_NETWORK_FILE_NAME = "myNetwork.txt";
    public static final String TEST_NETWORK_FILE_NAME = "test.txt";

    private Map<String, RouteProvider> routeProviderHashMap = new HashMap<>();
    private Map<String, Network> networkHashMap = new HashMap<>();

    private Controller() {}

    private void putNetworkToFile(Network network, String fileName) {
        try {
            new NetworkObjectSaver().writeNetwork(network, fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void putNetworkToFileWithJson(Network network, String fileName) {
        try {
            new NetworkJsonSaver().writeNetwork(network, fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addNetworkFromFile(String fileName) {
        try {
            addNetwork(new NetworkObjectSaver().readNetwork(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void addNetworkFromFileWithJson(String fileName) {
        try {
            addNetwork(new NetworkJsonSaver().readNetwork(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Controller getControllerWithAllProvidersAndNetworks() {
        Controller controller = new Controller();
        controller.addRouteProvider(new DomRu());
        //controller.addNetwork(new MyNetworkBuilder().getNetwork());
        //controller.addNetwork(new TestNetworkBuilder().getNetwork());
        controller.addNetworkFromFile(Controller.TEST_NETWORK_FILE_NAME);
        controller.addNetworkFromFile(Controller.MY_NETWORK_FILE_NAME);
        //controller.addNetworkFromFileWithJson(Controller.TEST_NETWORK_FILE_NAME);
        //controller.addNetworkFromFileWithJson(Controller.MY_NETWORK_FILE_NAME);

        controller.putNetworkToFile(new MyNetworkBuilder().getNetwork(), Controller.MY_NETWORK_FILE_NAME);
        controller.putNetworkToFile(new TestNetworkBuilder().getNetwork(), Controller.TEST_NETWORK_FILE_NAME);
        //controller.putNetworkToFileWithJson(new MyNetworkBuilder().getNetwork(), Controller.MY_NETWORK_FILE_NAME);
        //controller.putNetworkToFileWithJson(new TestNetworkBuilder().getNetwork(), Controller.TEST_NETWORK_FILE_NAME);
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
            try {
                new NetworkJsonSaver().readNetwork(net);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
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
