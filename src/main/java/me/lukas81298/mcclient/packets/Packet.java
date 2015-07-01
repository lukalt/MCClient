package me.lukas81298.mcclient.packets;

import java.io.IOException;

public interface Packet {

    public void serialize(PacketSerializer serializer) throws IOException;

    public void deserialize(PacketDeserializer deserializer) throws IOException;
    
    public int getPacketId();
    
}
