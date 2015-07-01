package me.lukas81298.mcclient.packets.login.client;

import java.io.IOException;

import me.lukas81298.mcclient.packets.Packet;
import me.lukas81298.mcclient.packets.PacketDeserializer;
import me.lukas81298.mcclient.packets.PacketSerializer;

public class PacketClientLoginRequest implements Packet {

    private String username;

    public PacketClientLoginRequest(String username) {
	this.username = username;
    }

    public PacketClientLoginRequest() {

    }

    @Override
    public void serialize(PacketSerializer serializer) throws IOException {
	serializer.writeVarInt(username.length());
	serializer.writeUTF(username);
    }

    @Override
    public void deserialize(PacketDeserializer deserializer) throws IOException {
	this.username = deserializer.readString();
    }

    public String getUsername() {
	return username;
    }

    public void setUsername(String username) {
	this.username = username;
    }

    @Override
    public int getPacketId() {
	return 0x00;
    }

}
