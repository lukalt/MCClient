package me.lukas81298.mcclient.packets.play.server;

import java.io.IOException;

import me.lukas81298.mcclient.packets.Packet;
import me.lukas81298.mcclient.packets.PacketDeserializer;
import me.lukas81298.mcclient.packets.PacketSerializer;

public class PacketServerTimeUpdate implements Packet {

    private long worldAge;
    private long timeOfDay;

    @Override
    public void serialize(PacketSerializer serializer) throws IOException {
    }

    @Override
    public void deserialize(PacketDeserializer d) throws IOException {
	worldAge = d.getDataIn().readLong();
	timeOfDay = d.getDataIn().readLong();
    }


    public long getWorldAge() {
        return worldAge;
    }

    public void setWorldAge(long worldAge) {
        this.worldAge = worldAge;
    }

    public long getTimeOfDay() {
        return timeOfDay;
    }

    public void setTimeOfDay(long timeOfDay) {
        this.timeOfDay = timeOfDay;
    }

    @Override
    public int getPacketId() {
	return 0x03;
    }

}
