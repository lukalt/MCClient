package me.lukas81298.mcclient.packets.play.server;

import java.io.IOException;

import me.lukas81298.mcclient.packets.Packet;
import me.lukas81298.mcclient.packets.PacketDeserializer;
import me.lukas81298.mcclient.packets.PacketSerializer;

public class PacketServerKeepAlive implements Packet {
    
    private int keepAliveId;

    public PacketServerKeepAlive(int keepAliveId) {
	super();
	this.keepAliveId = keepAliveId;
    }
    
    public PacketServerKeepAlive() {
	
    }

    public int getKeepAliveId() {
	return keepAliveId;
    }

    public void setKeepAliveId(int keepAliveId) {
	this.keepAliveId = keepAliveId;
    }

    @Override
    public void serialize(PacketSerializer serializer) throws IOException {
	serializer.writeVarInt(keepAliveId);
    }

    @Override
    public void deserialize(PacketDeserializer deserializer) throws IOException {
	this.keepAliveId = deserializer.readVarInt();
    }

    @Override
    public int getPacketId() {
	return 0x00;
    }

}
