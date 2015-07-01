package me.lukas81298.mcclient.packets.play.client;

import java.io.IOException;

import me.lukas81298.mcclient.packets.Packet;
import me.lukas81298.mcclient.packets.PacketDeserializer;
import me.lukas81298.mcclient.packets.PacketSerializer;

public class PacketClientKeepAlive implements Packet {

    private int keepLiveId;

    public PacketClientKeepAlive(int keepLiveId) {
	this.keepLiveId = keepLiveId;
    }
    
    @Override
    public void serialize(PacketSerializer serializer) throws IOException {
	serializer.writeVarInt(keepLiveId);
    }

    @Override
    public void deserialize(PacketDeserializer d) throws IOException {
    }

    @Override
    public int getPacketId() {
	return 0x00;
    }

}
