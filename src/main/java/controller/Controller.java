package controller;

import network.MyNetworkBuilder;
import network.Network;
import network.Path;
import network.TestNetworkBuilder;
import networkIO.NetworkJsonSaver;
import networkIO.NetworkObjectSaver;
import networkIO.NetworkSaver;
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
    private boolean jsonInfo = false;

    private Controller() {}

    public void setJsonInfo(boolean jsonInfo) {
        this.jsonInfo = jsonInfo;
    }

    private void putNetworkToFile(Network network, String fileName) {
        try {
            NetworkSaver mySaver = jsonInfo ? new NetworkJsonSaver() : new NetworkObjectSaver();
            mySaver.writeNetwork(network, fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Network getNetworkFromFile(String fileName) {
        try {
            NetworkSaver mySaver = jsonInfo ? new NetworkJsonSaver() : new NetworkObjectSaver();
            return mySaver.readNetwork(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Controller getControllerWithAllProvidersAndNetworks(boolean jsonInfo) {
        Controller controller = new Controller();
        controller.setJsonInfo(jsonInfo);
        controller.addRouteProvider(new DomRu());

        controller.putNetworkToFile(new MyNetworkBuilder().getNetwork(), Controller.MY_NETWORK_FILE_NAME);
        controller.putNetworkToFile(new TestNetworkBuilder().getNetwork(), Controller.TEST_NETWORK_FILE_NAME);

        //controller.addNetwork(new MyNetworkBuilder().getNetwork());
        //controller.addNetwork(new TestNetworkBuilder().getNetwork());
        controller.addNetwork(controller.getNetworkFromFile(Controller.TEST_NETWORK_FILE_NAME));
        controller.addNetwork(controller.getNetworkFromFile(Controller.MY_NETWORK_FILE_NAME));

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
        if (net.contains(".txt"))
            return getNetworkFromFile(net);
        return networkHashMap.get(net);
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
