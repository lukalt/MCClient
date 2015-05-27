package me.lukas81298.mcclient.packets.play.server;

import java.io.IOException;

import me.lukas81298.mcclient.packets.Packet;
import me.lukas81298.mcclient.packets.PacketDeserializer;
import me.lukas81298.mcclient.packets.PacketSerializer;

public class PacketServerSpawnPlayer implements Packet {

    @Override
    public void serialize(PacketSerializer serializer) throws IOException {
    }

    @Override
    public void deserialize(PacketDeserializer d) throws IOException {
//	this.entityId = d.readVarInt();
//	d.getDataIn().readLong();
//	d.getDataIn().readLong();
//	this.x = d.readInt();
//	this.y = d.readInt();
//	this.z = d.readInt();
//	this.yaw = d.readFloat();
//	this.pitch = d.readFloat();
//	
    }

    @Override
    public int getPacketId() {
	return 0x0C;
    }

}
