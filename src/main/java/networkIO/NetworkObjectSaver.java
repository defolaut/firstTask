package networkIO;

import network.Network;

import java.io.*;

public class NetworkObjectSaver implements NetworkSaver {
    @Override
    public void writeNetwork(Network network, String fileName) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(network);
        }
    }

    @Override
    public Network readNetwork(String fileName) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            return (Network) ois.readObject();
        }
    }
}
