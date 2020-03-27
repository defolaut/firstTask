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

    public static void addConnection(NetworkElement el1, NetworkElement el2) {
        el1.addConnection(el2);
        el2.addConnection(el1);
    }

    public static Network getTestNetwork() {
        Network net = new Network("test");
        PC pc1 = new PC(1);
        PC pc2 = new PC(2);
        PC pc5 = new PC(5);
        PC pc6 = new PC(6);
        PC pc7 = new PC(7);
        Firewall fw1 = new Firewall(3);
        Firewall fw2 = new Firewall(4);

        Network.addConnection(pc1, fw1);
        Network.addConnection(fw1, fw2);
        Network.addConnection(pc2, fw2);
        Network.addConnection(pc1, pc5);
        Network.addConnection(pc5, pc7);
        Network.addConnection(pc7, pc2);
        Network.addConnection(pc6, fw1);
        Network.addConnection(pc6, pc7);

        /*
            pc1 -- fw1 -- fw2 -- pc2
            |      |               |
            pc5    pc6             |
            |      |               |
            |------pc7-------------|
        */

        fw1.addLegalProvider("DomRu");
        fw2.addLegalProvider("DomRu");

        net.addPathElement(pc1);
        net.addPathElement(pc2);
        net.addPathElement(fw1);
        net.addPathElement(fw2);
        net.addPathElement(pc7);
        net.addPathElement(pc6);
        net.addPathElement(pc5);

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
