package networkIO;

import com.google.gson.*;
import elements.Firewall;
import elements.NetworkElement;
import elements.PC;

import java.lang.reflect.Type;

public class NetworkElementDeserializer implements JsonDeserializer<NetworkElement> {
    @Override
    public NetworkElement deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        NetworkElement result = null;

        String networkElementType = jsonObject.get("type").getAsString();
        int id = jsonObject.get("id").getAsInt();

        if ("PC".equals(networkElementType)) {
            result = new PC(id);
        } else if ("Firewall".equals(networkElementType)) {
            result = new Firewall(id);
            JsonArray legalProviders = jsonObject.getAsJsonArray("legalProviders");
            for (JsonElement providerName : legalProviders) {
                ((Firewall) result).addLegalProvider(providerName.getAsString());
            }
        }

        result.setCost(jsonObject.get("cost").getAsDouble());
        result.setTimeDelay(jsonObject.get("timeDelay").getAsDouble());
        result.setInfo(jsonObject.get("info").getAsString());

        return result;
    }
}
