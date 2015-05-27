package me.lukas81298.mcclient.packets.play.server;

import java.io.IOException;

import me.lukas81298.mcclient.packets.Packet;
import me.lukas81298.mcclient.packets.PacketDeserializer;
import me.lukas81298.mcclient.packets.PacketSerializer;

public class PacketServerUserBed implements Packet {

    private int entityId;
    private long location;

    @Override
    public void serialize(PacketSerializer serializer) throws IOException {
	
    }

    @Override
    public void deserialize(PacketDeserializer deserializer) throws IOException {
	this.entityId = deserializer.readVarInt();
	this.location = deserializer.getDataIn().readLong();
    }

    public int getEntityId() {
	return entityId;
    }

    public void setEntityId(int entityId) {
	this.entityId = entityId;
    }

    public long getLocation() {
	return location;
    }

    public void setLocation(long location) {
	this.location = location;
    }

    @Override
    public int getPacketId() {
	return 0x0A;
    }

}
