package me.lukas81298.mcclient.packets.play.server;

import java.io.IOException;

import me.lukas81298.mcclient.packets.Packet;
import me.lukas81298.mcclient.packets.PacketDeserializer;
import me.lukas81298.mcclient.packets.PacketSerializer;

public class PacketServerUpdateHealth implements Packet {

    private float foodSaturation;
    private float health;
    private int food;

    @Override
    public void serialize(PacketSerializer serializer) throws IOException {
    }

    @Override
    public void deserialize(PacketDeserializer d) throws IOException {
	health = d.readFloat();
	food = d.readVarInt();
	foodSaturation = d.readFloat();
    }

    public float getFoodSaturation() {
	return foodSaturation;
    }

    public float getHealth() {
	return health;
    }

    public int getFood() {
	return food;
    }

    @Override
    public int getPacketId() {
	return 0x06;
    }

}
