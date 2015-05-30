package me.lukas81298.mcclient.packets.play.server;

import java.io.IOException;

import me.lukas81298.mcclient.packets.Packet;
import me.lukas81298.mcclient.packets.PacketDeserializer;
import me.lukas81298.mcclient.packets.PacketSerializer;

public class PacketServerEntityEquipment implements Packet {

    private int entityId;
    private short slot;
    private short item;
    private byte count;
    private short damage = (short) 0;
    private byte randomByte = (byte) 0;

    @Override
    public void serialize(PacketSerializer serializer) throws IOException {
    }

    @Override
    public void deserialize(PacketDeserializer d) throws IOException {
	entityId = d.readVarInt();
	slot = d.readShort();
	item = d.readShort();
	if (this.item != -1) {
	    this.count = d.readByte();
	    this.damage = d.readShort();
	    this.randomByte = d.readByte();
	}
    }

    public byte getCount() {
	return count;
    }

    public void setCount(byte count) {
	this.count = count;
    }

    public short getDamage() {
	return damage;
    }

    public void setDamage(short damage) {
	this.damage = damage;
    }

    public byte getRandomByte() {
	return randomByte;
    }

    public void setRandomByte(byte randomByte) {
	this.randomByte = randomByte;
    }

    public int getEntityId() {
	return entityId;
    }

    public void setEntityId(int entityId) {
	this.entityId = entityId;
    }

    public short getSlot() {
	return slot;
    }

    public void setSlot(short slot) {
	this.slot = slot;
    }

    public short getItem() {
	return item;
    }

    public void setItem(short item) {
	this.item = item;
    }

    @Override
    public int getPacketId() {
	return 0x04;
    }

}
