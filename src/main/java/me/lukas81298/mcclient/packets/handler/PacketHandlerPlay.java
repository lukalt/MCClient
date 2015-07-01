package me.lukas81298.mcclient.packets.handler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import me.lukas81298.mcclient.MCClient;
import me.lukas81298.mcclient.packets.Packet;
import me.lukas81298.mcclient.packets.PacketDeserializer;
import me.lukas81298.mcclient.packets.ProtocolState;
import me.lukas81298.mcclient.packets.play.server.PacketServerAnimation;
import me.lukas81298.mcclient.packets.play.server.PacketServerAttachEntity;
import me.lukas81298.mcclient.packets.play.server.PacketServerCamera;
import me.lukas81298.mcclient.packets.play.server.PacketServerChangeGameState;
import me.lukas81298.mcclient.packets.play.server.PacketServerChat;
import me.lukas81298.mcclient.packets.play.server.PacketServerCombatEvent;
import me.lukas81298.mcclient.packets.play.server.PacketServerDifficulty;
import me.lukas81298.mcclient.packets.play.server.PacketServerEntityEquipment;
import me.lukas81298.mcclient.packets.play.server.PacketServerEntityStatus;
import me.lukas81298.mcclient.packets.play.server.PacketServerHeaderFooter;
import me.lukas81298.mcclient.packets.play.server.PacketServerHeldItemChange;
import me.lukas81298.mcclient.packets.play.server.PacketServerJoin;
import me.lukas81298.mcclient.packets.play.server.PacketServerKeepAlive;
import me.lukas81298.mcclient.packets.play.server.PacketServerPlayerAbilities;
import me.lukas81298.mcclient.packets.play.server.PacketServerPlayerList;
import me.lukas81298.mcclient.packets.play.server.PacketServerPluginMessage;
import me.lukas81298.mcclient.packets.play.server.PacketServerResourcePackSend;
import me.lukas81298.mcclient.packets.play.server.PacketServerRespawn;
import me.lukas81298.mcclient.packets.play.server.PacketServerSetCompression;
import me.lukas81298.mcclient.packets.play.server.PacketServerSoundEffect;
import me.lukas81298.mcclient.packets.play.server.PacketServerSpawnGlobalEntity;
import me.lukas81298.mcclient.packets.play.server.PacketServerSpawnPosition;
import me.lukas81298.mcclient.packets.play.server.PacketServerStatistics;
import me.lukas81298.mcclient.packets.play.server.PacketServerTimeUpdate;
import me.lukas81298.mcclient.packets.play.server.PacketServerTitle;
import me.lukas81298.mcclient.packets.play.server.PacketServerUpdateHealth;
import me.lukas81298.mcclient.packets.play.server.PacketServerUserBed;
import me.lukas81298.mcclient.packets.play.server.PacketServerWorldBorder;

public class PacketHandlerPlay implements PacketHandler {

    private Map<Integer, Class<? extends Packet>> packets = new HashMap<>();
    
    @Override
    public Packet handlePacket(int packetId, PacketDeserializer d) throws IOException {
	try {
	    Packet packet = getPacket(packetId);
	    packet.deserialize(d);
	    if(packet instanceof PacketServerKeepAlive) {
		PacketServerKeepAlive k = (PacketServerKeepAlive)packet;
		MCClient.getInstance().sendPacket(k);
	    }
	    MCClient.getInstance().handlePacket(packet);
	    return packet;
	} catch (InstantiationException e) {
	    e.printStackTrace();
	} catch (IllegalAccessException e) {
	    e.printStackTrace();
	} catch (RuntimeException e) {
	}
	return null;
    }
    
    public PacketHandlerPlay() {
	this.register(0x0, PacketServerKeepAlive.class);
	this.register(0x01, PacketServerJoin.class);
	this.register(0x02, PacketServerChat.class);
	this.register(0x03, PacketServerTimeUpdate.class);
	this.register(0x04, PacketServerEntityEquipment.class);
	this.register(0x05, PacketServerSpawnPosition.class);
	this.register(0x06, PacketServerUpdateHealth.class);
	this.register(0x07, PacketServerRespawn.class);
	this.register(0x09, PacketServerHeldItemChange.class);
	this.register(0x0A, PacketServerUserBed.class);
	this.register(0x0B, PacketServerAnimation.class);
	this.register(0x1A, PacketServerEntityStatus.class);
	this.register(0x1B, PacketServerAttachEntity.class);
	this.register(0x29, PacketServerSoundEffect.class);
	this.register(0x2b, PacketServerChangeGameState.class);
	this.register(0x2c, PacketServerSpawnGlobalEntity.class);
	this.register(0x37, PacketServerStatistics.class);
	this.register(0x38, PacketServerPlayerList.class);
	this.register(0x39, PacketServerPlayerAbilities.class);
	this.register(0x3f, PacketServerPluginMessage.class);
	this.register(0x41, PacketServerDifficulty.class);
	this.register(0x42, PacketServerCombatEvent.class);
	this.register(0x43, PacketServerCamera.class);
	this.register(0x44, PacketServerWorldBorder.class);
	this.register(0x45, PacketServerTitle.class);
	this.register(0x46, PacketServerSetCompression.class);
	this.register(0x47, PacketServerHeaderFooter.class);
	this.register(0x48, PacketServerResourcePackSend.class);
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
