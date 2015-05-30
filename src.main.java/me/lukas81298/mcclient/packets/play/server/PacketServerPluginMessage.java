package me.lukas81298.mcclient.packets.play.server;

import java.io.IOException;

import me.lukas81298.mcclient.packets.Packet;
import me.lukas81298.mcclient.packets.PacketDeserializer;
import me.lukas81298.mcclient.packets.PacketSerializer;

public class PacketServerPluginMessage implements Packet {

    private String channel;
    private String data;

    @Override
    public void serialize(PacketSerializer serializer) throws IOException {
    }

    @Override
    public void deserialize(PacketDeserializer deserializer) throws IOException {
	this.channel = deserializer.readStringFromBuffer(150);
	this.data = deserializer.readStringFromBuffer(320000);
    }

    @Override
    public int getPacketId() {
	return 0x3f;
    }

}
