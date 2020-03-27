package network;

import routeProviders.Visitor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
                Objects.equals(info, that.info);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timeDelay, cost, info, id);
    }
}
