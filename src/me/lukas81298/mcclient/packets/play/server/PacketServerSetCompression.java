package me.lukas81298.mcclient.packets.play.server;

import java.io.IOException;

import me.lukas81298.mcclient.packets.Packet;
import me.lukas81298.mcclient.packets.PacketDeserializer;
import me.lukas81298.mcclient.packets.PacketSerializer;

public class PacketServerSetCompression implements Packet {

    private int treshold;

    @Override
    public void serialize(PacketSerializer serializer) throws IOException {
    }

    @Override
    public void deserialize(PacketDeserializer deserializer) throws IOException {
	this.treshold = deserializer.readVarInt();
    }

    public int getTreshold() {
	return treshold;
    }

    public void setTreshold(int treshold) {
	this.treshold = treshold;
    }

    @Override
    public int getPacketId() {
	return 0x46;
    }

}
