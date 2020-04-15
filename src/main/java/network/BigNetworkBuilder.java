package network;

import elements.*;

public class BigNetworkBuilder implements NetworkBuilder {
    @Override
    public Network getNetwork() {
        Network network = new Network("bigNetwork");
        Hub hub0 = new Hub(0);
        Hub hub100 = new Hub(100);
        Cabel cabel = new Cabel(1000);
        cabel.setCost(10);
        cabel.setTimeDelay(10);
        NetworkUtils.addConnection(hub0, cabel);
        NetworkUtils.addConnection(hub100, cabel);
        Firewall firewall0 = new Firewall(1);
        Firewall firewall100 = new Firewall(101);
        firewall0.addLegalProvider("DomRu");
        firewall100.addLegalProvider("DomRu");
        NetworkUtils.addConnection(hub0, firewall0);
        NetworkUtils.addConnection(hub100, firewall100);
        network.addPathElement(hub0);
        network.addPathElement(hub100);
        network.addPathElement(cabel);
        network.addPathElement(firewall0);
        network.addPathElement(firewall100);

        NetworkElement prevPC = firewall0;
        for (int i = 2; i < 10; i++) {
            PC pc = new PC(i);
            NetworkUtils.addConnection(pc, prevPC);
            network.addPathElement(pc);
            prevPC = pc;
        }

        prevPC = firewall100;
        for (int i = 102; i < 110; i++) {
            PC pc = new PC(i);
            NetworkUtils.addConnection(pc, prevPC);
            network.addPathElement(pc);
            prevPC = pc;
        }
        return network;
    }
}
