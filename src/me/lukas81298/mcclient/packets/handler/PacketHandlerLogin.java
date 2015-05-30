package me.lukas81298.mcclient.packets.handler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import me.lukas81298.mcclient.MCClient;
import me.lukas81298.mcclient.packets.Packet;
import me.lukas81298.mcclient.packets.PacketDeserializer;
import me.lukas81298.mcclient.packets.ProtocolState;
import me.lukas81298.mcclient.packets.login.server.PacketServerLoginDisconnect;

public class PacketHandlerLogin implements PacketHandler {

    private Map<Integer, Class<? extends Packet>> packets = new HashMap<>();

    @Override
    public boolean handlePacket(int packetId, PacketDeserializer d) throws IOException {
	try {
	    Packet packet = getPacket(packetId);
	    packet.deserialize(d);
	    System.out.println(packet.getClass().getName());
	    return true;
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
	    break;
	case 0x01:
	    System.out.println("Online Mode not supported.");
	    break;
	default:
	    return false;
	}
	return true;
    }

    @Override
    public ProtocolState getProtocolState() {
	return ProtocolState.LOGGING_IN;
    }

    public PacketHandlerLogin() {
	this.register(0x00, PacketServerLoginDisconnect.class);
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
