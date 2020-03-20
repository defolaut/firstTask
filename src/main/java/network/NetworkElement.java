package network;

import java.util.List;

public abstract class NetworkElement implements PathElement {
    protected double timeDelay;
    protected double cost;
    protected List<PathElement> connections;
    protected String info;
    protected int id;

    public abstract void addConnection(PathElement pathElement);
}
