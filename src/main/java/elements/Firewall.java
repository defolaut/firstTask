package network.elements;

import routeProviders.Visitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Firewall extends ActiveElement {

    private HashMap<String, Boolean> legalProviders = new HashMap<>();

    public Firewall(int id) {
        super();
        setInfo("Firewall");
        setId(id);
    }

    @Override
    public List<PathElement> getConnections() {
        return new ArrayList<>();
    }

    public List<PathElement> getFirewallConnections(String providerName) {
        if (legalProviders.containsKey(providerName)) {
            return getConnections();
        }
        return new ArrayList<>();
    }

    public void addLegalProvider(String provideName) {
        legalProviders.put(provideName, Boolean.TRUE);
    }

    @Override
    public void visitorHandler(Visitor visitor) {
        if (legalProviders.containsKey(visitor.getVisitorProviderName())) {
            visitorProtectedHandler(visitor);
        }
    }

}
