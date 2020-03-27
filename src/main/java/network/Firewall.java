package network;

import routeProviders.Visitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Firewall extends ActiveElement {
    private HashMap<String, Boolean> legalProviders;

    {
        legalProviders = new HashMap<String, Boolean>();
    }

    public Firewall(int id) {
        super();
        info = "Firewall";
        this.id = id;
    }

    @Override
    public List<PathElement> getConnections() {
        return new ArrayList<PathElement>();
    }

    public List<PathElement> getFirewallConnections(String providerName) {
        if (legalProviders.containsKey(providerName)) {
            return connections;
        }
        return new ArrayList<PathElement>();
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
