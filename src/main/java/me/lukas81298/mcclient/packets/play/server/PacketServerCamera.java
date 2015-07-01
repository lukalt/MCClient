package me.lukas81298.mcclient.packets.play.server;

import java.io.IOException;

import me.lukas81298.mcclient.packets.Packet;
import me.lukas81298.mcclient.packets.PacketDeserializer;
import me.lukas81298.mcclient.packets.PacketSerializer;

public class PacketServerCamera implements Packet {

    private int cameraId;

    @Override
    public void serialize(PacketSerializer serializer) throws IOException {
    }

    @Override
    public void deserialize(PacketDeserializer d) throws IOException {
	this.cameraId = d.readVarInt();
    }

    public int getCameraId() {
	return cameraId;
    }

    public void setCameraId(int cameraId) {
	this.cameraId = cameraId;
    }

    @Override
    public int getPacketId() {
	return 0x43;
    }

}
