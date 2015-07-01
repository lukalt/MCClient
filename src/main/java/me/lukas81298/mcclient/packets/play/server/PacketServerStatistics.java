package me.lukas81298.mcclient.packets.play.server;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import me.lukas81298.mcclient.packets.Packet;
import me.lukas81298.mcclient.packets.PacketDeserializer;
import me.lukas81298.mcclient.packets.PacketSerializer;

public class PacketServerStatistics implements Packet {

    private Map<String, Integer> stats = new HashMap<>();

    @Override
    public void serialize(PacketSerializer serializer) throws IOException {
    }

    @Override
    public void deserialize(PacketDeserializer d) throws IOException {
	int amount = d.readVarInt();
	for(int i = 0; i < amount; i++) {
	    stats.put(d.readStringFromBuffer(1024), d.readVarInt());
	}
    }

    public Map<String,Integer> getStatistics() {
	return this.stats;
    }
    
    @Override
    public int getPacketId() {
	return 0x37;
    }
    
    
}
