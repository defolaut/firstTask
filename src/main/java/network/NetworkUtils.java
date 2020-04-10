package network;

import elements.NetworkElement;

public class NetworkUtils {

    public static void addConnection(NetworkElement el1, NetworkElement el2) {
        el1.addConnection(el2);
        el2.addConnection(el1);
    }

}
