package me.lukas81298.mcclient.packets.play.server;

import java.io.IOException;

import me.lukas81298.mcclient.packets.Packet;
import me.lukas81298.mcclient.packets.PacketDeserializer;
import me.lukas81298.mcclient.packets.PacketSerializer;

public class PacketServerAnimation implements Packet {

    private int entityId;
    private int animation;

    @Override
    public void serialize(PacketSerializer serializer) throws IOException {
    }

    @Override
    public void deserialize(PacketDeserializer deserializer) throws IOException {
	this.entityId = deserializer.readVarInt();
	this.animation = deserializer.readUnsignedByte();
    }

    public int getEntityId() {
	return entityId;
    }

    public void setEntityId(int entityId) {
	this.entityId = entityId;
    }

    public int getAnimation() {
	return animation;
    }

    public void setAnimation(int animation) {
	this.animation = animation;
    }

    @Override
    public int getPacketId() {
	return 0x0B;
    }

}
