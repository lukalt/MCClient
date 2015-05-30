package me.lukas81298.mcclient.packets.play.server;

import java.io.IOException;

import me.lukas81298.mcclient.packets.Packet;
import me.lukas81298.mcclient.packets.PacketDeserializer;
import me.lukas81298.mcclient.packets.PacketSerializer;

public class PacketServerWorldBorder implements Packet {

    private int action;
    private double newRadius;
    private double oldRadius;
    private long speed;
    private double radius;
    private int portal;
    private double x;
    private double z;
    private int warningTime;
    private int warningBlocks;

    @Override
    public void serialize(PacketSerializer serializer) throws IOException {
    }

    @Override
    public void deserialize(PacketDeserializer d) throws IOException {
	action = d.readVarInt();
	switch (action) {
	case 0:
	    this.radius = d.readDouble();
	    break;
	case 1:
	    this.oldRadius = d.readDouble();
	    this.newRadius = d.readDouble();
	    this.speed = d.readVarLong();
	    break;
	case 2:
	    this.x = d.readDouble();
	    this.z = d.readDouble();
	    break;
	case 3:
	    this.x = d.readDouble();
	    this.z = d.readDouble();
	    this.oldRadius = d.readDouble();
	    this.newRadius = d.readDouble();
	    this.speed = d.readVarLong();
	    this.portal = d.readVarInt();
	    this.warningTime = d.readVarInt();
	    this.warningBlocks = d.readVarInt();
	    break;
	case 4:
	    this.warningTime = d.readVarInt();
	    break;
	case 5:
	    this.warningBlocks = d.readVarInt();
	    break;
	}
    }

    public int getAction() {
	return action;
    }

    public void setAction(int action) {
	this.action = action;
    }

    public double getNewRadius() {
	return newRadius;
    }

    public void setNewRadius(double newRadius) {
	this.newRadius = newRadius;
    }

    public double getOldRadius() {
	return oldRadius;
    }

    public void setOldRadius(double oldRadius) {
	this.oldRadius = oldRadius;
    }

    public long getSpeed() {
	return speed;
    }

    public void setSpeed(long speed) {
	this.speed = speed;
    }

    public double getRadius() {
	return radius;
    }

    public void setRadius(double radius) {
	this.radius = radius;
    }

    public int getPortal() {
	return portal;
    }

    public void setPortal(int portal) {
	this.portal = portal;
    }

    public double getX() {
	return x;
    }

    public void setX(double x) {
	this.x = x;
    }

    public double getZ() {
	return z;
    }

    public void setZ(double z) {
	this.z = z;
    }

    public int getWarningTime() {
	return warningTime;
    }

    public void setWarningTime(int warningTime) {
	this.warningTime = warningTime;
    }

    public int getWarningBlocks() {
	return warningBlocks;
    }

    public void setWarningBlocks(int warningBlocks) {
	this.warningBlocks = warningBlocks;
    }

    @Override
    public int getPacketId() {
	return 0x44;
    }

}
