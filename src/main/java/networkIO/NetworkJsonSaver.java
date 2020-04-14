package networkIO;

import network.Network;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class NetworkJsonSaver implements NetworkSaver {
    @Override
    public void writeNetwork(Network network, String fileName) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            bw.write(NetworkJsonAssist.getJsonStringFromNetwork(network));
        }
    }

    @Override
    public Network readNetwork(String fileName) throws IOException, ClassNotFoundException {
        List<String> stringList = Files.readAllLines(Paths.get(fileName));
        String jsonString = "";
        for (int i = 0; i < stringList.size(); i++) {
            jsonString += stringList.get(i);
            if (i != stringList.size() - 1) {
                jsonString += "\n";
            }
        }
        return NetworkJsonAssist.getNetworkFromJsonString(jsonString);
    }
}
