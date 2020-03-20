package network;

import java.util.ArrayList;
import java.util.List;

public class Path {
    private List<PathElement> pathElements;

    public Path () {
        pathElements = new ArrayList<PathElement>();
    }

    public void addPathElement(PathElement pathElement) {
        pathElements.add(pathElement);
    }

    public List<PathElement> getPathElements() {
        return pathElements;
    }
}
