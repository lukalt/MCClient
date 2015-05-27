package me.lukas81298.mcclient.packets.play.server;

import java.io.IOException;

import me.lukas81298.mcclient.packets.Packet;
import me.lukas81298.mcclient.packets.PacketDeserializer;
import me.lukas81298.mcclient.packets.PacketSerializer;

public class PacketServerHeldItemChange implements Packet {
    
    private byte slot;

    @Override
    public void serialize(PacketSerializer serializer) throws IOException {
    }

    @Override
    public void deserialize(PacketDeserializer d) throws IOException {
	slot = d.readByte();
    }

    public byte getSlot() {
	return slot;
    }

    @Override
    public int getPacketId() {
	return 0x09;
    }
}
