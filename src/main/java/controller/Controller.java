package controller;

import network.Network;
import network.Path;
import routeProviders.DomRu;
import routeProviders.RouteNotFoundException;
import routeProviders.RouteProvider;
import routeProviders.Visitor;

import java.util.ArrayList;
import java.util.HashMap;

public class Controller {
    private HashMap<String, RouteProvider> routeProviderHashMap;
    private HashMap<String, Network> networkHashMap;

    public Controller() {
        routeProviderHashMap = new HashMap<String, RouteProvider>();
        networkHashMap = new HashMap<String, Network>();
    }

    public static Controller getControllerWithAllProvidersAndNetworks() {
        Controller controller = new Controller();
        controller.addRouteProvider(new DomRu());
        controller.addNetwork(Network.getTestNetwork());

        return controller;
    }

    private void addRouteProvider(RouteProvider routeProvider) {
        routeProviderHashMap.put(routeProvider.getProviderName(), routeProvider);
    }

    private void addNetwork(Network network) {
        networkHashMap.put(network.getNetworkName(), network);
    }

    public Path getPathByNetProviderAndTwoID(String net, String provider, int id1, int id2) throws RouteNotFoundException {
        if (!routeProviderHashMap.containsKey(provider) || !networkHashMap.containsKey(net)) {
            throw new RouteNotFoundException();
        }

        RouteProvider routeProvider = routeProviderHashMap.get(provider);
        Network network = networkHashMap.get(net);

        return routeProvider.getRoute(id1, id2, network);
    }

    public void printAllRoutes(String net, String provider, int id1, int id2) throws RouteNotFoundException {
        System.out.println("\nALL ROUTES BETWEEN id1 = " + id1 + " and id2 = " + id2 +
                        " in Network " + net + " with Provider " + provider + ":");

        int i = 1;
        for (Visitor visitor : routeProviderHashMap.get(provider).getAllVisitors(id1, id2, networkHashMap.get(net))) {
            System.out.println("\nRoute " + i + " is:");
            visitor.printVisitor();
            i++;
        }
    }

}
