package network;

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

    /** use it for file read/write */
    void connectionsIDInit();
    void addConnection(PathElement pathElement);
    boolean containID(Integer id);

}
