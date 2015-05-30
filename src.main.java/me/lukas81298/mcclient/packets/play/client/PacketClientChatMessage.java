package me.lukas81298.mcclient.packets.play.client;

import java.io.IOException;

import me.lukas81298.mcclient.packets.Packet;
import me.lukas81298.mcclient.packets.PacketDeserializer;
import me.lukas81298.mcclient.packets.PacketSerializer;

public class PacketClientChatMessage implements Packet {

    private String chatMessage;

    public PacketClientChatMessage(String chatMessage) {
	super();
	this.chatMessage = chatMessage;
    }

    public String getChatMessage() {
	return chatMessage;
    }

    public void setChatMessage(String chatMessage) {
	this.chatMessage = chatMessage;
    }

    @Override
    public void serialize(PacketSerializer serializer) throws IOException {
	serializer.writeVarInt(this.chatMessage.length());
	serializer.writeUTF(this.chatMessage);
    }

    @Override
    public void deserialize(PacketDeserializer deserializer) {
	
    }

    @Override
    public int getPacketId() {
	return 0x01;
    }

}
