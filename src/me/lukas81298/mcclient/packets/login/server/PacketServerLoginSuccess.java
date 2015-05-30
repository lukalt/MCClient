package me.lukas81298.mcclient.packets.login.server;

import java.io.IOException;

import me.lukas81298.mcclient.packets.Packet;
import me.lukas81298.mcclient.packets.PacketDeserializer;
import me.lukas81298.mcclient.packets.PacketSerializer;

public class PacketServerLoginSuccess implements Packet {

    private String username;
    private String uuid;

    @Override
    public void serialize(PacketSerializer serializer) throws IOException {
    }

    @Override
    public void deserialize(PacketDeserializer d) throws IOException {
	this.username = d.readStringFromBuffer(16);
	this.uuid = d.readStringFromBuffer(200);
    }

    public String getUsername() {
	return username;
    }

    public void setUsername(String username) {
	this.username = username;
    }

    public String getUuid() {
	return uuid;
    }

    public void setUuid(String uuid) {
	this.uuid = uuid;
    }

    @Override
    public int getPacketId() {
	return 0x02;
    }

}
