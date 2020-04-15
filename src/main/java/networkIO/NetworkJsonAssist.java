package networkIO;

import com.google.gson.*;
import elements.*;
import network.Network;

import java.util.ArrayList;
import java.util.List;

public class NetworkJsonAssist {

    public static String getJsonStringFromNetwork(Network network) {
        List<NetworkElementWrapper> networkElementWrappers = new ArrayList<>();
        for (PathElement pathElement : network.getPathElements()) {
            List<PathElement> connections = pathElement instanceof Firewall ?
                    ((Firewall) pathElement).getFirewallConnections("adminPASSWORD") :
                    pathElement.getConnections();
            List<Integer> ids = new ArrayList<>();
            for (PathElement connection : connections) {
                ids.add(connection.getID());
            }
            NetworkElement networkElement = (NetworkElement) pathElement;
            NetworkElementWrapper networkElementWrapper = new NetworkElementWrapper(
                    ids,
                    networkElement
            );
            networkElementWrappers.add(networkElementWrapper);
        }
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(NetworkElement.class, new NetworkElementSerializer())
                .registerTypeAdapter(NetworkElementWrapper.class, new NetworkElementWrapperSerializer())
                .registerTypeAdapter(PC.class,
                        (JsonSerializer<PC>) (pc, type, jsonSerializationContext)
                                -> new NetworkElementSerializer().serialize(pc, type, jsonSerializationContext))
                .registerTypeAdapter(Firewall.class,
                        (JsonSerializer<Firewall>) (firewall, type, jsonSerializationContext)
                                -> new NetworkElementSerializer().serialize(firewall, type, jsonSerializationContext))
                .registerTypeAdapter(Hub.class,
                        (JsonSerializer<Hub>) (hub, type, jsonSerializationContext)
                                -> new NetworkElementSerializer().serialize(hub, type, jsonSerializationContext))
                .registerTypeAdapter(Cabel.class,
                        (JsonSerializer<Cabel>) (cabel, type, jsonSerializationContext)
                                -> new NetworkElementSerializer().serialize(cabel, type, jsonSerializationContext))
                .create();
        return gson.toJson(new NetworkWrapper(network.getNetworkName(), networkElementWrappers));
    }

    public static Network getNetworkFromJsonString(String jsonString) throws ClassNotFoundException {
        try {
            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .registerTypeAdapter(NetworkElement.class, new NetworkElementDeserializer())
                    .registerTypeAdapter(NetworkElementWrapper.class, new NetworkElementWrapperDeserializer())
                    .create();
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
