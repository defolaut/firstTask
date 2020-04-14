package networkIO;

import elements.NetworkElement;

import java.util.List;

public class NetworkElementWrapper {
    private List<Integer> ids;
    private NetworkElement networkElement;

    public NetworkElementWrapper(List<Integer> ids, NetworkElement networkElement) {
        this.ids = ids;
        this.networkElement = networkElement;
    }

    public List<Integer> getIds() {
        return ids;
    }

    public NetworkElement getNetworkElement() {
        return networkElement;
    }
}
