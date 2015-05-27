package me.lukas81298.mcclient.packets.handler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import me.lukas81298.mcclient.packets.Packet;
import me.lukas81298.mcclient.packets.PacketDeserializer;
import me.lukas81298.mcclient.packets.ProtocolState;
import me.lukas81298.mcclient.packets.play.server.PacketServerAnimation;
import me.lukas81298.mcclient.packets.play.server.PacketServerChangeGameState;
import me.lukas81298.mcclient.packets.play.server.PacketServerDifficulty;
import me.lukas81298.mcclient.packets.play.server.PacketServerEntityEquipment;
import me.lukas81298.mcclient.packets.play.server.PacketServerHeldItemChange;
import me.lukas81298.mcclient.packets.play.server.PacketServerJoin;
import me.lukas81298.mcclient.packets.play.server.PacketServerKeepAlive;
import me.lukas81298.mcclient.packets.play.server.PacketServerRespawn;
import me.lukas81298.mcclient.packets.play.server.PacketServerSoundEffect;
import me.lukas81298.mcclient.packets.play.server.PacketServerSpawnGlobalEntity;
import me.lukas81298.mcclient.packets.play.server.PacketServerSpawnPosition;
import me.lukas81298.mcclient.packets.play.server.PacketServerTimeUpdate;
import me.lukas81298.mcclient.packets.play.server.PacketServerUpdateHealth;
import me.lukas81298.mcclient.packets.play.server.PacketServerUserBed;

public class PacketHandlerPlay implements PacketHandler {

    private Map<Integer, Class<? extends Packet>> packets = new HashMap<>();
    
    @Override
    public boolean handlePacket(int packetId, PacketDeserializer d) throws IOException {
	try {
	    Packet packet = getPacket(packetId);
	    System.out.println(packet.getClass().getName());
	} catch (InstantiationException e) {
	    e.printStackTrace();
	} catch (IllegalAccessException e) {
	    e.printStackTrace();
	} catch (RuntimeException e) {
	    e.printStackTrace();
	    System.out.println("0x" + Integer.toHexString(packetId) + " received.");
	    return false;
	}
	return true;
    }
    
    public PacketHandlerPlay() {
	this.register(0x0, PacketServerKeepAlive.class);
	this.register(0x1, PacketServerJoin.class);
	this.register(0x3, PacketServerTimeUpdate.class);
	this.register(0x4, PacketServerEntityEquipment.class);
	this.register(0x5, PacketServerSpawnPosition.class);
	this.register(0x6, PacketServerUpdateHealth.class);
	this.register(0x7, PacketServerRespawn.class);
	this.register(0x9, PacketServerHeldItemChange.class);
	this.register(0x0A, PacketServerUserBed.class);
	this.register(0x0B, PacketServerAnimation.class);
	
	this.register(0x29, PacketServerSoundEffect.class);
	this.register(0x2b, PacketServerChangeGameState.class);
	this.register(0x2c, PacketServerSpawnGlobalEntity.class);
	this.register(0x31, PacketServerDifficulty.class);

    }

    @Override
    public ProtocolState getProtocolState() {
	return ProtocolState.PLAY;
    }
    
    private void register(int packetId, Class<? extends Packet> packet) {
	this.packets.put(packetId, packet);
    }
    
    public Packet getPacket(int packetId) throws InstantiationException, IllegalAccessException, RuntimeException {
	if(!this.packets.containsKey(packetId)) {
	    throw new RuntimeException("Illegal packet " + packetId);
	}
	return this.packets.get(packetId).newInstance();
    }

}
