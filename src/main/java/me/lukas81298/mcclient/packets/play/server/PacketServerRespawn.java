package me.lukas81298.mcclient.packets.play.server;

import java.io.IOException;

import me.lukas81298.mcclient.packets.Packet;
import me.lukas81298.mcclient.packets.PacketDeserializer;
import me.lukas81298.mcclient.packets.PacketSerializer;

public class PacketServerRespawn implements Packet {

    private int difficulty;
    private String levelType;
    private int gamemode;
    private int dimension;

    @Override
    public void serialize(PacketSerializer serializer) throws IOException {
    }

    @Override
    public void deserialize(PacketDeserializer d) throws IOException {
	dimension = d.readInt();
	difficulty = d.readUnsignedByte();
	gamemode = d.readUnsignedByte();
	levelType = d.readStringFromBuffer(100);
    }

    public int getDifficulty() {
	return difficulty;
    }

    public String getLevelType() {
	return levelType;
    }

    public int getGamemode() {
	return gamemode;
    }

    public int getDimension() {
	return dimension;
    }

    @Override
    public int getPacketId() {
	return 0x07;
    }

}
