package me.lukas81298.mcclient.event;

import me.lukas81298.mcclient.packets.Packet;
import me.lukas81298.mcclient.packets.ProtocolState;

public interface Listener {

    public void onPacketSend(ProtocolState state, Packet packet);
    
    public void onPacketReceive(ProtocolState state, Packet packet);
    
    public void onConnectionStart();
    
    public void onProtocolStateSwitch(ProtocolState state);
    
}
