package networkIO;

import com.google.gson.*;
import elements.Firewall;
import elements.NetworkElement;

import java.lang.reflect.Type;

public class NetworkElementSerializer implements JsonSerializer<NetworkElement> {
    @Override
    public JsonElement serialize(NetworkElement networkElement, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject result = new JsonObject();

        result.addProperty("type", networkElement.getClass().getSimpleName());
        result.addProperty("id", networkElement.getID());
        result.addProperty("cost", networkElement.getCost());
        result.addProperty("timeDelay", networkElement.getTimeDelay());
        result.addProperty("info", networkElement.getInfo());

        if (networkElement instanceof Firewall) {
            JsonArray legalProviders = new JsonArray();
            for (String legalProvider : ((Firewall) networkElement).getLegalProviders()) {
                legalProviders.add(legalProvider);
            }
            result.add("legalProviders", legalProviders);
        }

        return result;
    }
}
