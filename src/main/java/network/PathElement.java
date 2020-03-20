package network;

import java.util.List;

public interface PathElement {
    double getTimeDelay();
    double getCost();
    List<PathElement> getConnections();
    String getInfo();
    int getID();
}
