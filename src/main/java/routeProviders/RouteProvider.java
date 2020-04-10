package routeProviders;

import network.Network;
import network.Path;

import java.util.ArrayList;

public interface RouteProvider {

    Path getRoute(int firstID, int secondID, Network net) throws RouteNotFoundException;

    String getProviderName();

    void addVisitor(Visitor visitor);

    ArrayList<Visitor> getAllVisitors(int firstID, int secondID, Network net) throws RouteNotFoundException;

}
