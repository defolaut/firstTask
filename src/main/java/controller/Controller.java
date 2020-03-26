package controller;

import network.Network;
import network.Path;
import routeProviders.DomRu;
import routeProviders.RouteNotFoundException;
import routeProviders.RouteProvider;

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
}
