package MVC;

import Server.Packet;

import java.util.stream.Stream;

public interface IController {
    public Packet process(Packet packet);
}
