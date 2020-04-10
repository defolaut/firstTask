package network.elements;

import routeProviders.Visitor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class NetworkElement implements PathElement {
    private double timeDelay;
    private double cost;
    private String info;
    private int id;

    /** Troubles with file read/write (Stack Over Flow) */
    private transient List<PathElement> connections;

    public void addConnection(PathElement pathElement) {
        if (connections == null) {
            connections = new ArrayList<>();
        }
        connections.add(pathElement);
    }

    public NetworkElement() {
        timeDelay = 1;
        cost = 1;
        connections = new ArrayList<>();
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

    public void setTimeDelay(double timeDelay) {
        this.timeDelay = timeDelay;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public void visitorHandler(Visitor visitor) {
        visitorProtectedHandler(visitor);
    }

    protected void visitorProtectedHandler(Visitor visitor) {
        if (visitor.getEndElement().equals(this)) {
            visitor.endPoint();
            return;
        }

        visitor.addPathElement(this);

        for (PathElement pathElement : connections) {
            if (!visitor.isContainPathElement(pathElement)) {
                pathElement.visitorHandler(visitor.myClone());
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NetworkElement that = (NetworkElement) o;
        return Double.compare(that.timeDelay, timeDelay) == 0 &&
                Double.compare(that.cost, cost) == 0 &&
                id == that.id &&
                Objects.equals(info, that.info) &&
                Objects.equals(connections, that.connections);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timeDelay, cost, info, id, connections);
    }
}
