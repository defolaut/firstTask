package networkIO;

import com.google.gson.*;
import elements.NetworkElement;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class NetworkElementWrapperDeserializer implements JsonDeserializer<NetworkElementWrapper> {
    @Override
    public NetworkElementWrapper deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();

        JsonArray jsonArray = jsonObject.getAsJsonArray("ids");
        List<Integer> ids = new ArrayList<>();
        for (JsonElement id : jsonArray) {
            ids.add(id.getAsInt());
        }

        NetworkElement networkElement = jsonDeserializationContext.deserialize(
                jsonObject.get("networkElement"),
                NetworkElement.class
        );

        return new NetworkElementWrapper(ids, networkElement);
    }
}
