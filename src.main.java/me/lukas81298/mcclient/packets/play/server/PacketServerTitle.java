package me.lukas81298.mcclient.packets.play.server;

import java.io.IOException;

import me.lukas81298.mcclient.packets.Packet;
import me.lukas81298.mcclient.packets.PacketDeserializer;
import me.lukas81298.mcclient.packets.PacketSerializer;

public class PacketServerTitle implements Packet {

    private int action;
    private String title;
    private String subtitle;
    private int stay;
    private int fadeOut;
    private int fadeIn;

    @Override
    public void serialize(PacketSerializer serializer) throws IOException {
    }

    @Override
    public void deserialize(PacketDeserializer d) throws IOException {
	this.action = d.readVarInt();
	switch(this.action) {
	case 0:
	this.title = d.readStringFromBuffer(Short.MAX_VALUE);
	break;
	case 1:
	this.subtitle = d.readStringFromBuffer(Short.MAX_VALUE);
	break;
	case 2:
	    this.fadeIn = d.readInt();
	    this.stay = d.readInt();
	    this.fadeOut = d.readInt();
	    break;
	case 3:
	case 4:
	    return;
	
	}
    }

    
    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public int getStay() {
        return stay;
    }

    public void setStay(int stay) {
        this.stay = stay;
    }

    public int getFadeOut() {
        return fadeOut;
    }

    public void setFadeOut(int fadeOut) {
        this.fadeOut = fadeOut;
    }

    public int getFadeIn() {
        return fadeIn;
    }

    public void setFadeIn(int fadeIn) {
        this.fadeIn = fadeIn;
    }

    @Override
    public int getPacketId() {
	return 0x45;
    }

}
