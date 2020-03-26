package network;

import java.util.ArrayList;
import java.util.List;

public abstract class NetworkElement implements PathElement {
    protected double timeDelay;
    protected double cost;
    protected List<PathElement> connections;
    protected String info;
    protected int id;

    public void addConnection(PathElement pathElement) {
        connections.add(pathElement);
    }

    public NetworkElement() {
        timeDelay = 1;
        cost = 1;
        connections = new ArrayList<PathElement>();
    }

    public double getTimeDelay() {
        return timeDelay;
    }

    public double getCost() {
        return cost;
    }

    public List<PathElement> getConnections() {
        return connections;
    }

    public String getInfo() {
        return info;
    }

    public int getID() {
        return id;
    }
}
