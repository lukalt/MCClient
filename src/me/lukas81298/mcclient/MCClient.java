package me.lukas81298.mcclient;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

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
	    instance = new MCClient();
	} catch (UnknownHostException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    private Socket socket;
    private DataOutputStream out;
    private DataInputStream in;
    private ProtocolState protocolState = null;
    private PacketHandler packetHandler = null;
    
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
    
    public MCClient() throws UnknownHostException, IOException {
	this.socket = new Socket("localhost", 25565);
	this.out = new DataOutputStream(this.socket.getOutputStream());
	this.in = new DataInputStream(this.socket.getInputStream());
	new Thread(new Runnable() {

	    @Override
	    public void run() {
		try {
		    System.out.println("Connected.");
		    changeState(ProtocolState.HANDSHAKING);
		    sendPacket(new PacketHandshake("127.0.0.1", (short) 25565, 2));
		    changeState(protocolState = ProtocolState.LOGGING_IN);
		    sendPacket(new PacketClientLoginRequest());
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
		    while (socket.isConnected()) {
			int rawLength = readVarInt(input);
			int packetId = readVarInt(input);
			int packetDataLength = rawLength - getVarIntSize(packetId);
			if (packetDataLength <= 0)
			    return;
			byte[] packetData = new byte[packetDataLength];
			input.readFully(packetData, 0, packetDataLength);
			PacketDeserializer dataWrapper = new PacketDeserializer(new DataInputStream(new ByteArrayInputStream(packetData)));

			if (!readPacket(packetId, dataWrapper)) {
			    input.skipBytes(packetDataLength);
			    System.out.println("Received Packet: x0" + Integer.toHexString(packetId) + " with length " + rawLength + "; skipping " + packetDataLength);
			} else {
			    System.out.println("Handled Packet: x0" + Integer.toHexString(packetId) + " with length " + rawLength);
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

    protected boolean readPacket(int packetId, PacketDeserializer dataWrapper) throws IOException {
	return this.packetHandler.handlePacket(packetId, dataWrapper);
    }

    private void sendPacket(Packet packet) throws IOException {
	ByteArrayOutputStream b = new ByteArrayOutputStream();
	DataOutputStream out = new DataOutputStream(b);
	PacketSerializer serializer = new PacketSerializer(out, b);
	packet.serialize(serializer);
	ProtocolUtil.writeVarInt(this.out, serializer.getPacketSize());
	this.out.write(b.toByteArray());
    }

    public static int getVarIntSize(int input) {
	for (int var1 = 1; var1 < 5; ++var1) {
	    if ((input & -1 << var1 * 7) == 0) {
		return var1;
	    }
	}

	return 5;
    }

    public static int readVarInt(DataInputStream in) throws IOException { // reads
									  // a
									  // varint
									  // from
									  // the
									  // stream
	int i = 0;
	int j = 0;
	while (true) {
	    int k = in.read();

	    i |= (k & 0x7F) << j++ * 7;

	    if (j > 5)
		throw new RuntimeException("VarInt too big");

	    if ((k & 0x80) != 128)
		break;
	}

	return i;
    }

    public static int[] readVarIntt(DataInputStream in) throws IOException { // reads
									     // a
									     // varint
									     // from
									     // the
									     // stream,
									     // returning
									     // both
									     // the
									     // length
									     // and
									     // the
									     // value
	int i = 0;
	int j = 0;
	int b = 0;
	while (true) {
	    int k = in.read();
	    b += 1;
	    i |= (k & 0x7F) << j++ * 7;

	    if (j > 5)
		throw new RuntimeException("VarInt too big");

	    if ((k & 0x80) != 128)
		break;
	}

	int[] result = {
		i, b
	};
	return result;
    }

    public void writeVarInt(DataOutput out, int paramInt) throws IOException {
	while (true) {
	    if ((paramInt & 0xFFFFFF80) == 0) {
		out.writeByte(paramInt);
		return;
	    }

	    out.writeByte(paramInt & 0x7F | 0x80);
	    paramInt >>>= 7;
	}
    }
}
