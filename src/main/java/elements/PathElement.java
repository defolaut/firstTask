package elements;

import routeProviders.Visitor;

import java.io.Serializable;
import java.util.List;

public interface PathElement extends Serializable {

    double getTimeDelay();
    double getCost();
    List<PathElement> getConnections();
    String getInfo();
    int getID();

    /** use it for finding all routes */
    void visitorHandler(Visitor visitor);

}
