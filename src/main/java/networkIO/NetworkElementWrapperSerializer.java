package networkIO;

import com.google.gson.*;

import java.lang.reflect.Type;

public class NetworkElementWrapperSerializer implements JsonSerializer<NetworkElementWrapper> {
    @Override
    public JsonElement serialize(NetworkElementWrapper networkElementWrapper, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject result = new JsonObject();

        JsonArray ids = new JsonArray();
        for (Integer id : networkElementWrapper.getIds()) {
            ids.add(id);
        }
        result.add("ids", ids);

        result.add("networkElement", jsonSerializationContext.serialize(networkElementWrapper.getNetworkElement()));
        return result;
    }
}
