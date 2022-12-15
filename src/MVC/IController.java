package MVC;

import Protocols.Packet;

public interface IController {
    public Packet process(Packet packet);
}
