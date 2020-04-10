package network;
import elements.PathElement;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Network implements Serializable {
    private String networkName;
    private List<PathElement> pathElements = new ArrayList<>();

    public Network(String networkName) {
        this.networkName = networkName;
    }

    public String getNetworkName() {
        return networkName;
    }

    public List<PathElement> getPathElements() {
        return pathElements;
    }

    public void addPathElement(PathElement pathElement) {
        pathElements.add(pathElement);
    }

    @Override
    public String toString() {
        return "Network{" +
                "networkName='" + networkName + '\'' +
                ", pathElements=" + pathElements +
                '}';
    }
}
