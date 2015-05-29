package me.lukas81298.mcclient.packets.play.server;

import java.io.IOException;

import me.lukas81298.mcclient.packets.Packet;
import me.lukas81298.mcclient.packets.PacketDeserializer;
import me.lukas81298.mcclient.packets.PacketSerializer;

public class PacketServerResourcePackSend implements Packet {

    private String url;
    private String hash;

    @Override
    public void serialize(PacketSerializer serializer) throws IOException {
    }

    @Override
    public void deserialize(PacketDeserializer deserializer) throws IOException {
	this.url = deserializer.readStringFromBuffer(Short.MIN_VALUE);
	this.hash = deserializer.readStringFromBuffer(Short.MIN_VALUE);
    }

    
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    @Override
    public int getPacketId() {
	return 0x48;
    }

}
