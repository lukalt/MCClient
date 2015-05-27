package me.lukas81298.mcclient.packets.handler;

import java.io.IOException;

import me.lukas81298.mcclient.packets.PacketDeserializer;
import me.lukas81298.mcclient.packets.ProtocolState;

public interface PacketHandler {

    public boolean handlePacket(int packetId, PacketDeserializer deserializer) throws IOException;
    
    public ProtocolState getProtocolState();
    
}
