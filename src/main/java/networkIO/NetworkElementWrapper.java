package networkIO;

import elements.NetworkElement;

import java.util.List;

public class NetworkElementWrapper {
    private String type;
    private List<Integer> ids;
    private NetworkElement networkElement;

    public NetworkElementWrapper(String type, List<Integer> ids, NetworkElement networkElement) {
        this.type = type;
        this.ids = ids;
        this.networkElement = networkElement;
    }

    public String getType() {
        return type;
    }

    public List<Integer> getIds() {
        return ids;
    }

    public NetworkElement getNetworkElement() {
        return networkElement;
    }
}
