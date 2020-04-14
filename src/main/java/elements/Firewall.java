package elements;

import routeProviders.Visitor;

import java.util.ArrayList;
import java.util.List;

public class Firewall extends ActiveElement {

    private List<String> legalProviders = new ArrayList<>();

    public Firewall(int id) {
        super();
        setInfo("Firewall");
        setId(id);
    }

    public List<String> getLegalProviders() {
        return legalProviders;
    }

    @Override
    public List<PathElement> getConnections() {
        return new ArrayList<>();
    }

    public List<PathElement> getFirewallConnections(String providerName) {
        if (legalProviders.contains(providerName)) {
            return getConnections();
        }
        return new ArrayList<>();
    }

    public void addLegalProvider(String provideName) {
        legalProviders.add(provideName);
    }

    @Override
    public void visitorHandler(Visitor visitor) {
        if (legalProviders.contains(visitor.getVisitorProviderName())) {
            visitorProtectedHandler(visitor);
        }
    }

}
