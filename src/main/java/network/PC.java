package network;

import routeProviders.Visitor;

public class PC extends ActiveElement {

    public PC(int id) {
        super();
        info = "PC";
        this.id = id;
    }

    @Override
    public void visitorHandler(Visitor visitor) {
        visitorProtectedHandler(visitor);
    }

}
