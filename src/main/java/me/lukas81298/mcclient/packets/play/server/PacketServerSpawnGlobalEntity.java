package me.lukas81298.mcclient.packets.play.server;

import java.io.IOException;

import me.lukas81298.mcclient.packets.Packet;
import me.lukas81298.mcclient.packets.PacketDeserializer;
import me.lukas81298.mcclient.packets.PacketSerializer;

public class PacketServerSpawnGlobalEntity implements Packet {

    private int entityId;
    private byte type;
    private int x;
    private int y;
    private int z;

    @Override
    public void serialize(PacketSerializer serializer) throws IOException {
    }

    @Override
    public void deserialize(PacketDeserializer deserializer) throws IOException {
	entityId = deserializer.readVarInt();
	type = deserializer.readByte();
	x = deserializer.getDataIn().readInt();
	y = deserializer.getDataIn().readInt();
	z = deserializer.getDataIn().readInt();
    }

    public int getEntityId() {
	return entityId;
    }

    public void setEntityId(int entityId) {
	this.entityId = entityId;
    }

    public byte getType() {
	return type;
    }

    public void setType(byte type) {
	this.type = type;
    }

    public int getX() {
	return x;
    }

    public void setX(int x) {
	this.x = x;
    }

    public int getY() {
	return y;
    }

    public void setY(int y) {
	this.y = y;
    }

    public int getZ() {
	return z;
    }

    public void setZ(int z) {
	this.z = z;
    }

    @Override
    public int getPacketId() {
	return 0x2c;
    }

}
