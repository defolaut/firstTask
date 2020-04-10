package networkIO;

import java.util.List;

public class NetworkWrapper {
    private String networkName;
    private List<NetworkElementWrapper> networkElementWrappers;

    public NetworkWrapper(String networkName, List<NetworkElementWrapper> networkElementWrappers) {
        this.networkName = networkName;
        this.networkElementWrappers = networkElementWrappers;
    }

    public String getNetworkName() {
        return networkName;
    }

    public List<NetworkElementWrapper> getNetworkElementWrappers() {
        return networkElementWrappers;
    }
}
