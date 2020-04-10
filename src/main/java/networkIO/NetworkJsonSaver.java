package networkUtils;

import network.Network;

import java.io.*;

public class NetworkJsonSaver implements NetworkSaver {
    @Override
    public void writeNetwork(Network network, String fileName) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            bw.write(NetworkJsonAssist.getJsonStringFromNetwork(network));
        }
    }

    @Override
    public Network readNetwork(String fileName) throws IOException, ClassNotFoundException {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            return NetworkJsonAssist.getNetworkFromJsonString(br.readLine());
        }
    }
}
