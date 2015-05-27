package me.lukas81298.mcclient.packets.login.client;

import java.io.IOException;

import me.lukas81298.mcclient.packets.Packet;
import me.lukas81298.mcclient.packets.PacketDeserializer;
import me.lukas81298.mcclient.packets.PacketSerializer;

public class PacketClientLoginRequest implements Packet {

    @Override
    public void serialize(PacketSerializer serializer) throws IOException {
	serializer.writeByte(0x00);
	String username = "Gronkh";
	serializer.writeVarInt(username.length());
	serializer.writeUTF(username);
    }

    @Override
    public void deserialize(PacketDeserializer deserializer) {
    }

    @Override
    public int getPacketId() {
	return 0x00;
    }

}
