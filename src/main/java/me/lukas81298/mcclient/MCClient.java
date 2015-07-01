package me.lukas81298.mcclient;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import me.lukas81298.mcclient.event.Listener;
import me.lukas81298.mcclient.packets.Packet;
import me.lukas81298.mcclient.packets.PacketDeserializer;
import me.lukas81298.mcclient.packets.PacketSerializer;
import me.lukas81298.mcclient.packets.ProtocolState;
import me.lukas81298.mcclient.packets.handler.PacketHandler;
import me.lukas81298.mcclient.packets.handler.PacketHandlerHandshake;
import me.lukas81298.mcclient.packets.handler.PacketHandlerLogin;
import me.lukas81298.mcclient.packets.handler.PacketHandlerPlay;
import me.lukas81298.mcclient.packets.handshake.PacketHandshake;
import me.lukas81298.mcclient.packets.login.client.PacketClientLoginRequest;

public class MCClient {

    private static MCClient instance;

    public static MCClient getInstance() {
	return instance;
    }

    public static void main(String[] args) {
	try {
	    instance = new MCClient(new Settings("lukas81298", "127.0.0.1", 25565));
	} catch (UnknownHostException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    private Settings settings;
    private Socket socket;
    private DataOutputStream out;
    private DataInputStream in;
    private ProtocolState protocolState = null;
    private PacketHandler packetHandler = null;
    private List<Listener> listeners = new ArrayList<>();
    private String username;

    public String getUsername() {
	return this.username;
    }

    public PacketHandler getPacketHandler() {
	return this.packetHandler;
    }

    public ProtocolState getProtocolState() {
	return this.protocolState;
    }

    public Socket getSocket() {
	return socket;
    }

    public DataOutputStream getOut() {
	return out;
    }

    public DataInputStream getIn() {
	return in;
    }

    public List<Listener> getListeners() {
	return this.listeners;
    }

    public void registerListener(Listener listener) {
	this.listeners.add(listener);
    }

    public void unregisterListener(Listener listener) {
	this.listeners.remove(listener);
    }

    public Settings getSettings() {
	return this.settings;
    }

    public void changeState(ProtocolState state) {
	this.protocolState = state;
	switch (state) {
	case HANDSHAKING:
	    packetHandler = new PacketHandlerHandshake();
	    break;
	case LOGGING_IN:
	    packetHandler = new PacketHandlerLogin();
	    break;
	case PLAY:
	    packetHandler = new PacketHandlerPlay();
	    break;
	}
    }

    public MCClient(Settings settings) throws UnknownHostException, IOException {
	this.settings = settings;
	this.socket = new Socket("localhost", 25565);
	this.out = new DataOutputStream(this.socket.getOutputStream());
	this.in = new DataInputStream(this.socket.getInputStream());
	new Thread(new Runnable() {

	    @Override
	    public void run() {
		try {
		    changeState(ProtocolState.HANDSHAKING);
		    sendPacket(new PacketHandshake(MCClient.this.settings.getServer(), (short) MCClient.this.settings.getPort(), 2, 47));
		    sendPacket(new PacketClientLoginRequest(MCClient.this.settings.getUsername()));
		    System.out.println("Logged in.");
		} catch (IOException e1) {
		    e1.printStackTrace();
		}

	    }
	}).start();
	new Thread(new Runnable() {

	    @Override
	    public void run() {
		try {
		    final DataInputStream input = MCClient.this.in;
		    while (socket.isConnected() && !socket.isClosed()) {
			int rawLength = ProtocolUtil.readVarInt(input);
			int packetId = ProtocolUtil.readVarInt(input);
			int packetDataLength = rawLength - ProtocolUtil.getVarIntSize(packetId);
			if (packetDataLength <= 0)
			    return;
			byte[] packetData = new byte[packetDataLength];
			input.readFully(packetData, 0, packetDataLength);
			PacketDeserializer dataWrapper = new PacketDeserializer(new DataInputStream(new ByteArrayInputStream(packetData)));

			Packet p = readPacket(packetId, dataWrapper);
			if(p != null) {
			    handlePacket(p);;
			}

		    }
		    try {
			Thread.sleep(50000);
		    } catch (InterruptedException e) {
			e.printStackTrace();
		    }
		} catch (IOException e1) {
		    e1.printStackTrace();
		}

	    }
	}).start();
    }

    protected Packet readPacket(int packetId, PacketDeserializer dataWrapper) throws IOException {
	Packet handlePacket = this.packetHandler.handlePacket(packetId, dataWrapper);
	return handlePacket;
    }

    public void handlePacket(Packet packet) {
	for (Listener l : listeners) {
	    l.onPacketReceive(protocolState, packet);
	}
    }

    public void sendPacket(Packet packet) throws IOException {
	ByteArrayOutputStream b = new ByteArrayOutputStream();
	DataOutputStream out = new DataOutputStream(b);
	PacketSerializer serializer = new PacketSerializer(out, b);
	serializer.writeByte(packet.getPacketId());
	packet.serialize(serializer);
	ProtocolUtil.writeVarInt(this.out, serializer.getPacketSize());
	this.out.write(b.toByteArray());
	for (Listener listener : listeners) {
	    listener.onPacketSend(protocolState, packet);
	}
    }

    public void shutdown() {
	try {
	    this.in.close();
	    this.out.close();
	    this.socket.close();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }
}
