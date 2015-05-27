package me.lukas81298.mcclient.packets.play.server;

import java.io.IOException;

import me.lukas81298.mcclient.packets.Packet;
import me.lukas81298.mcclient.packets.PacketDeserializer;
import me.lukas81298.mcclient.packets.PacketSerializer;

public class PacketServerDifficulty implements Packet {

    private int difficulty;

    public PacketServerDifficulty() {

    }

    public PacketServerDifficulty(int difficulty) {
	super();
	this.difficulty = difficulty;
    }

    public int getDifficulty() {
	return difficulty;
    }

    public void setDifficulty(int difficulty) {
	this.difficulty = difficulty;
    }

    @Override
    public void serialize(PacketSerializer serializer) throws IOException {
    }

    @Override
    public void deserialize(PacketDeserializer deserializer) throws IOException {
	this.difficulty = deserializer.readUnsignedByte();
    }

    @Override
    public int getPacketId() {
	return 0x41;
    }

}
