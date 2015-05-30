package me.lukas81298.mcclient.packets.play.server;

import java.io.IOException;

import me.lukas81298.mcclient.packets.Packet;
import me.lukas81298.mcclient.packets.PacketDeserializer;
import me.lukas81298.mcclient.packets.PacketSerializer;

public class PacketServerAttachEntity implements Packet {

    private int entityId;
    private int vehicleId;
    private boolean leashed;

    @Override
    public void serialize(PacketSerializer serializer) throws IOException {
    }

    @Override
    public void deserialize(PacketDeserializer d) throws IOException {
	this.entityId = d.readInt();
	this.vehicleId = d.readInt();
	this.leashed = d.readBoolean();
    }

    public int getEntityId() {
	return entityId;
    }

    public void setEntityId(int entityId) {
	this.entityId = entityId;
    }

    public int getVehicleId() {
	return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
	this.vehicleId = vehicleId;
    }

    public boolean isLeashed() {
	return leashed;
    }

    public void setLeashed(boolean leashed) {
	this.leashed = leashed;
    }

    @Override
    public int getPacketId() {
	return 0x1b;
    }

}
