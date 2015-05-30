package me.lukas81298.mcclient.packets.handler;

import me.lukas81298.mcclient.packets.Packet;
import me.lukas81298.mcclient.packets.PacketDeserializer;
import me.lukas81298.mcclient.packets.ProtocolState;

public class PacketHandlerHandshake implements PacketHandler {

    @Override
    public Packet handlePacket(int packetId, PacketDeserializer deserializer) {
	return null;
    }

    @Override
    public ProtocolState getProtocolState() {
	return ProtocolState.HANDSHAKING;
    }

}
