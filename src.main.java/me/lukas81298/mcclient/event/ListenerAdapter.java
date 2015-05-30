package me.lukas81298.mcclient.event;

import me.lukas81298.mcclient.packets.Packet;
import me.lukas81298.mcclient.packets.ProtocolState;

public class ListenerAdapter implements Listener {

    @Override
    public void onPacketSend(ProtocolState state, Packet packet) {
	
    }

    @Override
    public void onPacketReceive(ProtocolState state, Packet packet) {
	
    }

    @Override
    public void onConnectionStart() {
	
    }

    @Override
    public void onProtocolStateSwitch(ProtocolState state) {
	
    }

}
