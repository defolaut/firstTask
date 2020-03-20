package network;

import java.util.ArrayList;
import java.util.List;

public class PC extends ActiveElement {
    public PC() {
        timeDelay = 1;
        cost = 1;
        info = "PC";
        connections = new ArrayList<PathElement>();
    }

    public PC(int id) {
        this();
        this.id = id;
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

    @Override
    public void addConnection(PathElement pathElement) {
        connections.add(pathElement);
    }
}
