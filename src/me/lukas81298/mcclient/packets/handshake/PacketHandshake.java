package me.lukas81298.mcclient.packets.handshake;

import java.io.IOException;

import me.lukas81298.mcclient.packets.Packet;
import me.lukas81298.mcclient.packets.PacketDeserializer;
import me.lukas81298.mcclient.packets.PacketSerializer;

public class PacketHandshake implements Packet {

    private String server;
    private short port;
    private int state;

    public PacketHandshake(String server, short port, int state) {
	super();
	this.server = server;
	this.port = port;
	this.state = state;
    }

    
    public String getServer() {
        return server;
    }


    public void setServer(String server) {
        this.server = server;
    }


    public short getPort() {
        return port;
    }


    public void setPort(short port) {
        this.port = port;
    }
    
    public int getState() {
        return state;
    }


    public void setState(int state) {
        this.state = state;
    }


    @Override
    public void serialize(PacketSerializer serializer) throws IOException {
	serializer.writeByte(0x00);
	serializer.writeVarInt(47);
	serializer.writeVarInt(this.server.length());
	serializer.writeUTF(this.server);
	serializer.writeShort(this.port);	
	serializer.writeVarInt(2);
    }


    @Override
    public void deserialize(PacketDeserializer deserializer) {
    }


    @Override
    public int getPacketId() {
	return 0x00;
    }

}
