package me.lukas81298.mcclient.packets.play.server;

import java.io.IOException;

import me.lukas81298.mcclient.packets.Packet;
import me.lukas81298.mcclient.packets.PacketDeserializer;
import me.lukas81298.mcclient.packets.PacketSerializer;

public class PacketServerChat implements Packet {

    private String chat;

    private byte position;

    @Override
    public void serialize(PacketSerializer serializer) throws IOException {
    }

    @Override
    public void deserialize(PacketDeserializer deserializer) throws IOException {
	chat = deserializer.readStringFromBuffer(32000);
	position = deserializer.readByte();
	System.out.println(chat + "\n" + position);
    }

    public String getChat() {
	return chat;
    }

    public void setChat(String chat) {
	this.chat = chat;
    }

    public byte getPosition() {
	return position;
    }

    public void setPosition(byte position) {
	this.position = position;
    }

    @Override
    public int getPacketId() {
	return 0x02;
    }

}
