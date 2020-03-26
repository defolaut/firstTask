package routeProviders;

public class RouteNotFoundException extends Exception {
    @Override
    public String getMessage() {
        return "ROUTE NOT FOUND";
    }
}
