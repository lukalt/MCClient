package me.lukas81298.mcclient.packets.play.server;

import java.io.IOException;

import me.lukas81298.mcclient.packets.Packet;
import me.lukas81298.mcclient.packets.PacketDeserializer;
import me.lukas81298.mcclient.packets.PacketSerializer;

public class PacketServerSpawnPosition implements Packet {

    
    @Override
    public void serialize(PacketSerializer serializer) throws IOException {
	
    }

    @Override
    public void deserialize(PacketDeserializer deserializer) throws IOException {
	deserializer.getDataIn().readLong();
    }

    @Override
    public int getPacketId() {
	return 0x05;
    }

}
