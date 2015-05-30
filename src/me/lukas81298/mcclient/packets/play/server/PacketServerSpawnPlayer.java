package me.lukas81298.mcclient.packets.play.server;

import java.io.IOException;

import me.lukas81298.mcclient.packets.Packet;
import me.lukas81298.mcclient.packets.PacketDeserializer;
import me.lukas81298.mcclient.packets.PacketSerializer;

public class PacketServerSpawnPlayer implements Packet {

    private int entityId;
    private int x;
    private int y;
    private int z;
    private float yaw;
    private float pitch;

    @Override
    public void serialize(PacketSerializer serializer) throws IOException {
    }

    @Override
    public void deserialize(PacketDeserializer d) throws IOException {
	this.entityId = d.readVarInt();
	d.getDataIn().readLong();
	d.getDataIn().readLong();
	this.x = d.readInt();
	this.y = d.readInt();
	this.z = d.readInt();
	this.yaw = d.readFloat();
	this.pitch = d.readFloat();

    }

    public int getEntityId() {
	return entityId;
    }

    public void setEntityId(int entityId) {
	this.entityId = entityId;
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

    public float getYaw() {
	return yaw;
    }

    public void setYaw(float yaw) {
	this.yaw = yaw;
    }

    public float getPitch() {
	return pitch;
    }

    public void setPitch(float pitch) {
	this.pitch = pitch;
    }

    @Override
    public int getPacketId() {
	return 0x0C;
    }

}
