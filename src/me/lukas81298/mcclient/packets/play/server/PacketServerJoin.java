package me.lukas81298.mcclient.packets.play.server;

import java.io.IOException;

import me.lukas81298.mcclient.packets.Packet;
import me.lukas81298.mcclient.packets.PacketDeserializer;
import me.lukas81298.mcclient.packets.PacketSerializer;

public class PacketServerJoin implements Packet {

    private int entityId;

    private byte gamemode, dimension;

    private int difficulty, maxPlayers;

    private String levelType;

    private boolean reducedDebugInfo;

    
    public PacketServerJoin(int entityId, byte gamemode, byte dimension, int difficulty, int maxPlayers, String levelType, boolean reducedDebugInfo) {
	super();
	this.entityId = entityId;
	this.gamemode = gamemode;
	this.dimension = dimension;
	this.difficulty = difficulty;
	this.maxPlayers = maxPlayers;
	this.levelType = levelType;
	this.reducedDebugInfo = reducedDebugInfo;
    }

    public PacketServerJoin() {
	
    }
    
    @Override
    public void serialize(PacketSerializer serializer) throws IOException {

    }

    public int getEntityId() {
	return entityId;
    }

    public void setEntityId(int entityId) {
	this.entityId = entityId;
    }

    public byte getGamemode() {
	return gamemode;
    }

    public void setGamemode(byte gamemode) {
	this.gamemode = gamemode;
    }

    public byte getDimension() {
	return dimension;
    }

    public void setDimension(byte dimension) {
	this.dimension = dimension;
    }

    public int getDifficulty() {
	return difficulty;
    }

    public void setDifficulty(int difficulty) {
	this.difficulty = difficulty;
    }

    public int getMaxPlayers() {
	return maxPlayers;
    }

    public void setMaxPlayers(int maxPlayers) {
	this.maxPlayers = maxPlayers;
    }

    public String getLevelType() {
	return levelType;
    }

    public void setLevelType(String levelType) {
	this.levelType = levelType;
    }

    public boolean isReducedDebugInfo() {
	return reducedDebugInfo;
    }

    public void setReducedDebugInfo(boolean reducedDebugInfo) {
	this.reducedDebugInfo = reducedDebugInfo;
    }

    @Override
    public void deserialize(PacketDeserializer d) throws IOException {
	entityId = d.getDataIn().readInt();
	gamemode = (byte) d.getDataIn().readUnsignedByte();
	dimension = d.getDataIn().readByte();
	difficulty = d.getDataIn().readUnsignedByte();
	maxPlayers = d.getDataIn().readUnsignedByte();
	levelType = d.readStringFromBuffer(100);
	reducedDebugInfo = d.readBoolean();
    }

    @Override
    public int getPacketId() {
	return 0x01;
    }

}
