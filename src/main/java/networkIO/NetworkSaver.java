package networkIO;

import network.Network;

import java.io.IOException;

public interface NetworkSaver {
    void writeNetwork(Network network, String fileName) throws IOException;

    Network readNetwork(String fileName) throws IOException, ClassNotFoundException;
}
