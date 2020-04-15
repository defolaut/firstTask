package routeProviders;

import elements.PathElement;

import java.util.ArrayList;
import java.util.List;

public class Visitor {
    private PathElement endElement;
    private RouteProvider myProvider;
    private List<PathElement> pathElements = new ArrayList<>();

    public Visitor(RouteProvider routeProvider, PathElement endElement) {
        myProvider = routeProvider;
        this.endElement = endElement;
    }

    public void addPathElement(PathElement pathElement) {
        pathElements.add(pathElement);
    }

    public String getVisitorProviderName() {
        return myProvider.getProviderName();
    }

    public PathElement getEndElement() {
        return endElement;
    }

    public boolean isContainPathElement(PathElement pathElement) {
        return pathElements.contains(pathElement);
    }

    public void endPoint() {
        myProvider.addVisitor(this);
    }

    public Visitor myClone() {
        Visitor newVisitor = new Visitor(myProvider, endElement);

        for (PathElement pathElement : pathElements) {
            newVisitor.addPathElement(pathElement);
        }

        return newVisitor;
    }

    public void printVisitor() {
        int totalCost = 0;
        int totalDelay = 0;
        for (PathElement pathElement : pathElements) {
            totalCost += pathElement.getCost();
            totalDelay += pathElement.getTimeDelay();
            System.out.print(pathElement.getInfo() + " " + pathElement.getID() + " -> ");
        }
        System.out.print(endElement.getInfo() + " " + endElement.getID() + ".");

        System.out.println("\nTotal Cost = " + totalCost + " Total Delay = " + totalDelay);
    }

}
