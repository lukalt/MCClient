package me.lukas81298.mcclient.packets.handler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import me.lukas81298.mcclient.MCClient;
import me.lukas81298.mcclient.packets.Packet;
import me.lukas81298.mcclient.packets.PacketDeserializer;
import me.lukas81298.mcclient.packets.PacketSerializer;
import me.lukas81298.mcclient.packets.ProtocolState;
import me.lukas81298.mcclient.packets.login.server.PacketServerLoginDisconnect;
import me.lukas81298.mcclient.packets.login.server.PacketServerLoginSuccess;

public class PacketHandlerLogin implements PacketHandler {

    private Map<Integer, Class<? extends Packet>> packets = new HashMap<>();

    @Override
    public Packet handlePacket(int packetId, PacketDeserializer d) throws IOException {
	try {
	    Packet packet = getPacket(packetId);
	    packet.deserialize(d);
	    if(packet.getPacketId() == 0x00) {
		System.out.println("Disconnected while Loggin In: " + ((PacketServerLoginDisconnect)packet).getReason());
		MCClient.getInstance().shutdown();
	    }
	    if(packet.getPacketId() == 0x02) {
		MCClient.getInstance().changeState(ProtocolState.PLAY);
	    }
	    return packet;
	} catch (InstantiationException e) {
	    e.printStackTrace();
	} catch (IllegalAccessException e) {
	    e.printStackTrace();
	} catch (RuntimeException e) {
	    System.out.println("0x" + Integer.toHexString(packetId) + " received.");
	}
	switch (packetId) {
	case 0x03:
	    System.out.println("compression: " + d.readVarInt());
	    break;
	case 0x02:
	    System.out.println("Login Success");
	    String s = d.readStringFromBuffer(100);
	    String b = d.readStringFromBuffer(100);
	    System.out.println(s + ":" + b);
	    MCClient.getInstance().changeState(ProtocolState.PLAY);
	    return new Packet() {
	        
	        @Override
	        public void serialize(PacketSerializer serializer) throws IOException {
	        }
	        
	        @Override
	        public int getPacketId() {
	    	return 0;
	        }
	        
	        @Override
	        public void deserialize(PacketDeserializer deserializer) throws IOException {
	        }
	    };
	case 0x01:
	    System.out.println("Online Mode not supported.");
	    break;
	}
	return null;
    }

    @Override
    public ProtocolState getProtocolState() {
	return ProtocolState.LOGGING_IN;
    }

    public PacketHandlerLogin() {
	this.register(0x00, PacketServerLoginDisconnect.class);
	this.register(0x02, PacketServerLoginSuccess.class);
    }
    
    private void register(int packetId, Class<? extends Packet> packet) {
	this.packets.put(packetId, packet);
    }

    public Packet getPacket(int packetId) throws InstantiationException, IllegalAccessException, RuntimeException {
	if (!this.packets.containsKey(packetId)) {
	    throw new RuntimeException("Illegal packet " + packetId);
	}
	return this.packets.get(packetId).newInstance();
    }
}
