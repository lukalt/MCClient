package me.lukas81298.mcclient.packets.play.server;

import java.io.IOException;

import me.lukas81298.mcclient.packets.Packet;
import me.lukas81298.mcclient.packets.PacketDeserializer;
import me.lukas81298.mcclient.packets.PacketSerializer;

public class PacketServerEntityStatus implements Packet {

    private byte status;
    private int entityId;

    @Override
    public void serialize(PacketSerializer serializer) throws IOException {
    }

    @Override
    public void deserialize(PacketDeserializer deserializer) throws IOException {
	entityId =deserializer.readInt();
	status = deserializer.readByte();
    }

    
    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public int getEntityId() {
        return entityId;
    }

    public void setEntityId(int entityId) {
        this.entityId = entityId;
    }

    @Override
    public int getPacketId() {
	return 0x1;
    }

}
