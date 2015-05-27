package me.lukas81298.mcclient.packets.play.server;

import java.io.IOException;

import me.lukas81298.mcclient.packets.Packet;
import me.lukas81298.mcclient.packets.PacketDeserializer;
import me.lukas81298.mcclient.packets.PacketSerializer;

public class PacketServerSoundEffect implements Packet {

    private String name;
    private int x;
    private int y;
    private int z;
    private float volume;
    private int pitch;

    @Override
    public void serialize(PacketSerializer serializer) throws IOException {
    }

    @Override
    public void deserialize(PacketDeserializer d) throws IOException {
	name = d.readStringFromBuffer(120);
	x = d.readInt();
	y = d.readInt();
	z = d.readInt();
	volume = d.readFloat();
	pitch = d.readUnsignedByte();
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
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

    public float getVolume() {
	return volume;
    }

    public void setVolume(float volume) {
	this.volume = volume;
    }

    public int getPitch() {
	return pitch;
    }

    public void setPitch(int pitch) {
	this.pitch = pitch;
    }

    @Override
    public int getPacketId() {
	return 0x29;
    }

}
