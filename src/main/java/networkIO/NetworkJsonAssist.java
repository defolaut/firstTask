package networkUtils;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import elements.NetworkElement;
import elements.PathElement;
import network.Network;

import java.util.ArrayList;
import java.util.List;

public class NetworkJsonAssist {

    public static String getJsonStringFromNetwork(Network network) {
        List<NetworkElementWrapper> networkElementWrappers = new ArrayList<>();
        for (PathElement pathElement : network.getPathElements()) {
            List<PathElement> connections = pathElement.getConnections();
            List<Integer> ids = new ArrayList<>();
            for (PathElement connection : connections) {
                ids.add(connection.getID());
            }
            NetworkElement networkElement = (NetworkElement) pathElement;
            NetworkElementWrapper networkElementWrapper = new NetworkElementWrapper(networkElement.getClass().getTypeName(),
                    ids,
                    networkElement
            );
            networkElementWrappers.add(networkElementWrapper);
        }
        Gson gson = new Gson();
        return gson.toJson(new NetworkWrapper(network.getNetworkName(), networkElementWrappers));
    }

    public static Network getNetworkFromJsonString(String jsonString) throws ClassNotFoundException {
        try {
            Gson gson = new Gson();
            NetworkWrapper networkWrapper = gson.fromJson(jsonString, NetworkWrapper.class);
            Network network = new Network(networkWrapper.getNetworkName());
            for (NetworkElementWrapper networkElementWrapper : networkWrapper.getNetworkElementWrappers()) {
                NetworkElement networkElement = networkElementWrapper.getNetworkElement();
                for (NetworkElementWrapper networkElementWrapper1 : networkWrapper.getNetworkElementWrappers()) {
                    if (networkElementWrapper1.getIds().contains(networkElement.getID())) {
                        networkElement.addConnection(networkElementWrapper1.getNetworkElement());
                    }
                }
                network.addPathElement(networkElement);
            }
            return network;
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            throw new ClassNotFoundException();
        }
    }

}
