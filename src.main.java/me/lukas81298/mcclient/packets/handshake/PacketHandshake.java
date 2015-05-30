package me.lukas81298.mcclient.packets.handshake;

import java.io.IOException;

import me.lukas81298.mcclient.packets.Packet;
import me.lukas81298.mcclient.packets.PacketDeserializer;
import me.lukas81298.mcclient.packets.PacketSerializer;

public class PacketHandshake implements Packet {

    private String server;
    private short port;
    private int state;
    private int version = 47;
    
    public PacketHandshake(String server, short port, int state, int version) {
	super();
	this.server = server;
	this.port = port;
	this.state = state;
	this.version = version;
    }

    public PacketHandshake() {
	
    }
    
    public int getVersion() {
        return version;
    }



    public void setVersion(int version) {
        this.version = version;
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
	serializer.writeVarInt(this.version);
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
