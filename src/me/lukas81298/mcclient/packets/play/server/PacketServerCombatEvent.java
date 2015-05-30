package me.lukas81298.mcclient.packets.play.server;

import java.io.IOException;

import me.lukas81298.mcclient.packets.Packet;
import me.lukas81298.mcclient.packets.PacketDeserializer;
import me.lukas81298.mcclient.packets.PacketSerializer;

public class PacketServerCombatEvent implements Packet {

    private int event;
    private int duration;
    private int entityId;
    private int playerId;
    private String message;

    @Override
    public void serialize(PacketSerializer serializer) throws IOException {
    }

    @Override
    public void deserialize(PacketDeserializer d) throws IOException {
	this.event = d.readVarInt();
	if (this.event == 1) {
	    this.duration = d.readVarInt();
	    this.entityId = d.readInt();
	}
	if (this.event == 2) {
	    this.playerId = d.readVarInt();
	    this.entityId = d.readInt();
	    this.message = d.readStringFromBuffer(32767);
	}
    }

    public int getEvent() {
	return event;
    }

    public void setEvent(int event) {
	this.event = event;
    }

    public int getDuration() {
	return duration;
    }

    public void setDuration(int duration) {
	this.duration = duration;
    }

    public int getEntityId() {
	return entityId;
    }

    public void setEntityId(int entityId) {
	this.entityId = entityId;
    }

    public int getPlayerId() {
	return playerId;
    }

    public void setPlayerId(int playerId) {
	this.playerId = playerId;
    }

    public String getMessage() {
	return message;
    }

    public void setMessage(String message) {
	this.message = message;
    }

    @Override
    public int getPacketId() {
	return 0x42;
    }

}
