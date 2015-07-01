package me.lukas81298.mcclient.packets.play.server;

import java.io.IOException;
import java.util.UUID;

import me.lukas81298.mcclient.packets.Packet;
import me.lukas81298.mcclient.packets.PacketDeserializer;
import me.lukas81298.mcclient.packets.PacketSerializer;
import me.lukas81298.mcclient.packets.play.server.PacketServerPlayerList.PlayerListItem.Property;

public class PacketServerPlayerList implements Packet {

    private int amount;
    private int action;
    private PlayerListItem[] items;
    
    @Override
    public void serialize(PacketSerializer serializer) throws IOException {
    }

    @Override
    public void deserialize(PacketDeserializer d) throws IOException {
	this.action = d.readVarInt();
	this.amount = d.readVarInt();
	this.items = new PlayerListItem[this.amount];
	for (int i = 0; i < amount; i++) {
	    PlayerListItem item = new PlayerListItem(d.readUUID());
	    switch(this.action) {
	    case 0:
		item.name = d.readStringFromBuffer(16);
		int prop = d.readVarInt();
		item.properties = new Property[prop];
		for(int t = 0; t < prop; t++) {
		    String l1 = d.readStringFromBuffer(Short.MAX_VALUE);
		    String l2 = d.readStringFromBuffer(Short.MAX_VALUE);
		    boolean l3 = d.readBoolean();
		    Property p = new Property(l1, l2, l3, l3 ? d.readStringFromBuffer(Short.MAX_VALUE) : null);
		    item.properties[t] = p;
		}
		item.gamemode = d.readVarInt();
		item.ping = d.readVarInt();
		item.hasDisplayName = d.readBoolean();
		if(item.hasDisplayName) {
		    item.displayName = d.readStringFromBuffer(256);
		}
		break;
	    case 1:
		item.gamemode = d.readVarInt();
		break;
	    case 2:
		item.ping = d.readVarInt();
		break;
	    case 3:
		item.hasDisplayName = d.readBoolean();
		if(item.hasDisplayName) {
		    item.displayName = d.readStringFromBuffer(256);
		}
		break;
	    case 4:
		break;
	    default:
		new IllegalArgumentException("Invalid action id " + this.action);
	    }
	    this.items[i] = item;
	}
    }

    @Override
    public int getPacketId() {
	return 0x38;
    }

    public static class PlayerListItem {
	private String name;
	private int gamemode;
	private int ping;
	private boolean hasDisplayName;
	private String displayName;
	private UUID uuid;
	public Property[] properties = new Property[0];
	
	public PlayerListItem(UUID uuid) {
	    this.uuid = uuid;
	}

	public String getName() {
	    return name;
	}

	public void setName(String name) {
	    this.name = name;
	}

	public int getGamemode() {
	    return gamemode;
	}

	public void setGamemode(int gamemode) {
	    this.gamemode = gamemode;
	}

	public int getPing() {
	    return ping;
	}

	public void setPing(int ping) {
	    this.ping = ping;
	}

	public boolean isHasDisplayName() {
	    return hasDisplayName;
	}

	public void setHasDisplayName(boolean hasDisplayName) {
	    this.hasDisplayName = hasDisplayName;
	}

	public String getDisplayName() {
	    return displayName;
	}

	public void setDisplayName(String displayName) {
	    this.displayName = displayName;
	}

	public UUID getUuid() {
	    return uuid;
	}

	public void setUuid(UUID uuid) {
	    this.uuid = uuid;
	}

	public static class Property {
	    private String name;
	    private String value;
	    private boolean signed;
	    private String hash;

	    public Property(String name, String value, boolean signed, String hash) {
		super();
		this.name = name;
		this.value = value;
		this.signed = signed;
		this.hash = hash;
	    }

	    public String getName() {
		return name;
	    }

	    public void setName(String name) {
		this.name = name;
	    }

	    public String getValue() {
		return value;
	    }

	    public void setValue(String value) {
		this.value = value;
	    }

	    public boolean isSigned() {
		return signed;
	    }

	    public void setSigned(boolean signed) {
		this.signed = signed;
	    }

	    public String getHash() {
		return hash;
	    }

	    public void setHash(String hash) {
		this.hash = hash;
	    }

	}
    }

}
