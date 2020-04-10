package network.elements;

import routeProviders.Visitor;

import java.util.List;

public interface PathElement {

    double getTimeDelay();
    double getCost();
    List<PathElement> getConnections();
    String getInfo();
    int getID();

    /** use it for finding all routes */
    void visitorHandler(Visitor visitor);

}
