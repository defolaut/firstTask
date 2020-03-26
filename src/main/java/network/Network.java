package network;

import java.util.ArrayList;
import java.util.List;

public class Network {
    private String networkName;
    private List<PathElement> pathElements;

    public Network(String networkName) {
        pathElements = new ArrayList<PathElement>();
        this.networkName = networkName;
    }

    public static Network getTestNetwork() {
        Network net = new Network("test");
        PC pc1 = new PC(1);
        PC pc2 = new PC(2);
        Firewall fw1 = new Firewall(3);
        Firewall fw2 = new Firewall(4);

        pc1.addConnection(fw1);
        fw1.addConnection(pc1);
        fw1.addConnection(fw2);
        fw2.addConnection(fw1);
        fw2.addConnection(pc2);
        pc2.addConnection(fw2);

        fw1.addLegalProvider("DomRu");
        fw2.addLegalProvider("DomRu");

        net.addPathElement(pc1);
        net.addPathElement(pc2);
        net.addPathElement(fw1);
        net.addPathElement(fw2);

        return net;
    }

    public String getNetworkName() {
        return networkName;
    }

    public void setNetworkName(String networkName) {
        this.networkName = networkName;
    }

    public List<PathElement> getPathElements() {
        return pathElements;
    }

    public void addPathElement(PathElement pathElement) {
        pathElements.add(pathElement);
    }
}
