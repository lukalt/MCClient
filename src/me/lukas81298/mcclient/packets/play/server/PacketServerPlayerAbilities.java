package me.lukas81298.mcclient.packets.play.server;

import java.io.IOException;

import me.lukas81298.mcclient.packets.Packet;
import me.lukas81298.mcclient.packets.PacketDeserializer;
import me.lukas81298.mcclient.packets.PacketSerializer;

public class PacketServerPlayerAbilities implements Packet {

    private byte flags;
    private float flyingSpeed;
    private float walkingSpeed;

    @Override
    public void serialize(PacketSerializer serializer) throws IOException {
    }

    @Override
    public void deserialize(PacketDeserializer deserializer) throws IOException {
	this.flags = deserializer.readByte();
	this.flyingSpeed = deserializer.readFloat();
	this.walkingSpeed = deserializer.readFloat();
    }

    public byte getFlags() {
	return flags;
    }

    public void setFlags(byte flags) {
	this.flags = flags;
    }

    public float getFlyingSpeed() {
	return flyingSpeed;
    }

    public void setFlyingSpeed(float flyingSpeed) {
	this.flyingSpeed = flyingSpeed;
    }

    public float getWalkingSpeed() {
	return walkingSpeed;
    }

    public void setWalkingSpeed(float walkingSpeed) {
	this.walkingSpeed = walkingSpeed;
    }

    @Override
    public int getPacketId() {
	return 0x39;
    }

}
