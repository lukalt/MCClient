package me.lukas81298.mcclient.packets.play.client;

import java.io.IOException;

import me.lukas81298.mcclient.packets.Packet;
import me.lukas81298.mcclient.packets.PacketDeserializer;
import me.lukas81298.mcclient.packets.PacketSerializer;

public class PacketClientSettings implements Packet {

    private String locale;
    private int viewDistance;
    private int chatMode;
    private boolean chatColors;

    public PacketClientSettings(String locale, int viewDistance, int chatMode, boolean chatColors) {
	super();
	this.locale = locale;
	this.viewDistance = viewDistance;
	this.chatMode = chatMode;
	this.chatColors = chatColors;
    }

    public String getLocale() {
	return locale;
    }

    public void setLocale(String locale) {
	this.locale = locale;
    }

    public int getViewDistance() {
	return viewDistance;
    }

    public void setViewDistance(int viewDistance) {
	this.viewDistance = viewDistance;
    }

    public int getChatMode() {
	return chatMode;
    }

    public void setChatMode(int chatMode) {
	this.chatMode = chatMode;
    }

    public boolean isChatColors() {
	return chatColors;
    }

    public void setChatColors(boolean chatColors) {
	this.chatColors = chatColors;
    }

    @Override
    public void serialize(PacketSerializer s) throws IOException {
	s.writeUTF(locale);
	s.writeByte(viewDistance);
	s.writeByte(chatMode);
	s.getOut().writeBoolean(chatColors);
	s.writeByte(0 & 0xFF);
    }

    @Override
    public void deserialize(PacketDeserializer d) throws IOException {
    }

    @Override
    public int getPacketId() {
	return 0x15;
    }

}
