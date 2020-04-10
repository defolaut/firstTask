package network;

import elements.Firewall;
import elements.PC;

public class TestNetworkBuilder implements NetworkBuilder{
    @Override
    public Network getNetwork() {
        Network net = new Network("test");
        PC pc1 = new PC(1);
        PC pc2 = new PC(2);
        PC pc5 = new PC(5);
        PC pc6 = new PC(6);
        PC pc7 = new PC(7);
        Firewall fw1 = new Firewall(3);
        Firewall fw2 = new Firewall(4);

        NetworkUtils.addConnection(pc1, fw1);
        NetworkUtils.addConnection(fw1, fw2);
        NetworkUtils.addConnection(pc2, fw2);
        NetworkUtils.addConnection(pc1, pc5);
        NetworkUtils.addConnection(pc5, pc7);
        NetworkUtils.addConnection(pc7, pc2);
        NetworkUtils.addConnection(pc6, fw1);
        NetworkUtils.addConnection(pc6, pc7);

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
}
