package network;

import elements.PathElement;

import java.util.ArrayList;
import java.util.List;

public class Path {
    private List<PathElement> pathElements;

    public Path () {
        pathElements = new ArrayList<>();
    }

    public void addPathElement(PathElement pathElement) {
        pathElements.add(pathElement);
    }

    public List<PathElement> getPathElements() {
        return pathElements;
    }
}
