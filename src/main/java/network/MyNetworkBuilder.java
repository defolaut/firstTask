package network;

import elements.PC;

public class MyNetworkBuilder implements NetworkBuilder {
    @Override
    public Network getNetwork() {
        Network myNetwork = new Network("myNetwork");
        PC pc1 = new PC(1);
        PC pc2 = new PC(2);
        PC pc3 = new PC(3);
        PC pc4 = new PC(4);

        NetworkUtils.addConnection(pc1, pc2);
        NetworkUtils.addConnection(pc2, pc4);
        NetworkUtils.addConnection(pc1, pc3);
        NetworkUtils.addConnection(pc3, pc4);

        /*
            pc1----pc2
            |       |
            pc3----pc4
        */

        myNetwork.addPathElement(pc1);
        myNetwork.addPathElement(pc2);
        myNetwork.addPathElement(pc3);
        myNetwork.addPathElement(pc4);

        return myNetwork;
    }
}
