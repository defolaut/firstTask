package routeProviders;

import network.*;
import elements.Firewall;
import elements.PathElement;

import java.util.*;

public class DomRu implements RouteProvider {

    private String providerName;
    private ArrayList<Visitor> visitors = new ArrayList<>();

    public DomRu() {
        this.providerName = "DomRu";
    }

    private List<PathElement> getConnectionsFromPathElement(PathElement pathElement) {
        if (pathElement.getInfo().equals("Firewall")) {
            return ((Firewall)pathElement).getFirewallConnections(providerName);
        }
        return pathElement.getConnections();
    }

    private Path getPathByBFS(PathElement startNode, PathElement endNode) throws RouteNotFoundException {
        Path path = new Path();
        path.addPathElement(startNode);

        HashMap<PathElement, PathElement> parents = new HashMap<>();
        parents.put(startNode, null);

        ArrayDeque<PathElement> queue = new ArrayDeque<>();
        queue.add(startNode);

        while (!queue.isEmpty()) {
            PathElement from = queue.peek();
            queue.poll();

            for (PathElement to : getConnectionsFromPathElement(from)) {
                if (!parents.containsKey(to)) {
                    queue.add(to);
                    parents.put(to, from);
                }
            }
        }

        if (!parents.containsKey(endNode)) {
            throw new RouteNotFoundException();
        }

        List<PathElement> route = new ArrayList<PathElement>();
        while (!endNode.equals(startNode)) {
            route.add(endNode);
            endNode = parents.get(endNode);
        }
        Collections.reverse(route);

        for (PathElement pathElement : route) {
            path.addPathElement(pathElement);
        }
        return path;
    }

    private PathElement getPathElementFromID(int id, Network net) throws RouteNotFoundException {
        List<PathElement> nodes = net.getPathElements();
        for (PathElement pathElement : nodes) {
            if (pathElement.getID() == id) {
                return pathElement;
            }
        }

        throw new RouteNotFoundException();
    }

    public Path getRoute(int firstID, int secondID, Network net) throws RouteNotFoundException {
        PathElement startNode = getPathElementFromID(firstID, net);
        PathElement endNode = getPathElementFromID(secondID, net);

        return getPathByBFS(startNode, endNode);
    }

    public String getProviderName() {
        return providerName;
    }

    @Override
    public void addVisitor(Visitor visitor) {
        visitors.add(visitor);
    }

    @Override
    public ArrayList<Visitor> getAllVisitors(int firstID, int secondID, Network net) throws RouteNotFoundException {
        PathElement startElement = getPathElementFromID(firstID, net);
        PathElement endElement = getPathElementFromID(secondID, net);

        Visitor visitor = new Visitor(this, endElement);

        startElement.visitorHandler(visitor);

        return visitors;
    }
}
