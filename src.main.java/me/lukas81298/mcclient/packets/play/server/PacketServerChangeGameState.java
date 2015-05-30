package me.lukas81298.mcclient.packets.play.server;

import java.io.IOException;

import me.lukas81298.mcclient.packets.Packet;
import me.lukas81298.mcclient.packets.PacketDeserializer;
import me.lukas81298.mcclient.packets.PacketSerializer;

public class PacketServerChangeGameState implements Packet {

    private int reason;
    private float value;

    @Override
    public void serialize(PacketSerializer serializer) throws IOException {
    }

    @Override
    public void deserialize(PacketDeserializer deserializer) throws IOException {
	this.reason = deserializer.readVarInt();
	this.value = deserializer.getDataIn().readFloat();
    }

    public int getReason() {
	return reason;
    }

    public void setReason(int reason) {
	this.reason = reason;
    }

    public float getValue() {
	return value;
    }

    public void setValue(float value) {
	this.value = value;
    }

    @Override
    public int getPacketId() {
	return 0x2B;
    }

}
