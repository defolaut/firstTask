package network;

import routeProviders.Visitor;

import java.util.List;

public interface PathElement {

    double getTimeDelay();
    double getCost();
    List<PathElement> getConnections();
    String getInfo();
    int getID();

    void visitorHandler(Visitor visitor);
}
