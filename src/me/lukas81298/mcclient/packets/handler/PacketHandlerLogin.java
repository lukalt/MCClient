package me.lukas81298.mcclient.packets.handler;

import java.io.IOException;

import me.lukas81298.mcclient.MCClient;
import me.lukas81298.mcclient.packets.PacketDeserializer;
import me.lukas81298.mcclient.packets.ProtocolState;

public class PacketHandlerLogin implements PacketHandler {

    @Override
    public boolean handlePacket(int packetId, PacketDeserializer d) throws IOException {
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
	case 0x00:
	    System.out.println("Disconnected: " + d.readStringFromBuffer(1024));
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

}
