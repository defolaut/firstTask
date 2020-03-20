package routeProvider;

import network.IPAdress;
import network.Network;
import network.Path;
import network.PathElement;

import java.util.*;

public class DomRu implements RouteProvider {
    private String providerName;

    public DomRu() {
        this.providerName = "DomRu";
    }

    private Path getPathByBFS(PathElement startNode, PathElement endNode) throws RouteNotFoundException {
        Path path = new Path();
        path.addPathElement(startNode);

        HashMap<PathElement, PathElement> parents = new HashMap<PathElement,PathElement>();
        parents.put(startNode, null);

        PriorityQueue<PathElement> queue = new PriorityQueue<PathElement>();
        queue.add(startNode);

        while (!queue.isEmpty()) {
            PathElement from = queue.peek();
            queue.poll();

            for (PathElement to : from.getConnections()) {
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

    public Path getRoute(int firstID, int secondID, Network net) throws RouteNotFoundException {
        List<PathElement> nodes = net.getPathElements();
        PathElement startNode = null;
        PathElement endNode = null;
        for (PathElement pathElement : nodes) {
            if (pathElement.getID() == firstID) {
                startNode = pathElement;
            }
            if (pathElement.getID() == secondID) {
                endNode = pathElement;
            }
        }

        if (startNode.equals(null) || endNode.equals(null)) {
            throw new RouteNotFoundException();
        }
        return getPathByBFS(startNode, endNode);
    }

    public Path getRouteByIP(IPAdress firstIP, IPAdress secondIP, Network net) throws RouteNotFoundException {
        return null;
    }

    public String getProviderName() {
        return providerName;
    }
}
