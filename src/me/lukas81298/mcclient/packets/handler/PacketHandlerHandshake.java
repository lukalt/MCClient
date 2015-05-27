package me.lukas81298.mcclient.packets.handler;

import me.lukas81298.mcclient.packets.PacketDeserializer;
import me.lukas81298.mcclient.packets.ProtocolState;

public class PacketHandlerHandshake implements PacketHandler {

    @Override
    public boolean handlePacket(int packetId, PacketDeserializer deserializer) {
	return false;
    }

    @Override
    public ProtocolState getProtocolState() {
	return ProtocolState.HANDSHAKING;
    }

}
