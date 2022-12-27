package MVC;

import Protocols.Packet;

public interface IController <T> {
    public Packet<T> process(Packet<T> packet);
}
