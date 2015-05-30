package me.lukas81298.mcclient.packets;

import java.io.DataInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.UUID;

public class PacketDeserializer {

    private DataInputStream dataIn;

    public PacketDeserializer(DataInputStream dataIn) {
	super();
	this.dataIn = dataIn;
    }

    public DataInputStream getDataIn() {
	return dataIn;
    }

    public void setDataIn(DataInputStream dataIn) {
	this.dataIn = dataIn;
    }

    public int readVarInt() throws IOException {
	int i = 0;
	int j = 0;
	while (true) {
	    int k = dataIn.readByte();
	    i |= (k & 0x7F) << j++ * 7;
	    if (j > 5)
		throw new RuntimeException("VarInt too big");
	    if ((k & 0x80) != 128)
		break;
	}
	return i;
    }

    public short readShort() {
	try {
	    return dataIn.readShort();
	} catch (IOException e) {
	    e.printStackTrace();
	}
	return 0;
    }

    public byte readByte() {
	try {
	    return dataIn.readByte();
	} catch (IOException e) {
	    e.printStackTrace();
	}
	return 0;
    }

    public String readUTF() {
	try {
	    return this.dataIn.readUTF();
	} catch (IOException e) {
	    e.printStackTrace();
	}
	return null;
    }

    public String readString() throws IOException {
	int length = this.readVarInt();
	String s = this.readUTF();
	if (s.length() != length) {
	    throw new RuntimeException("Invalid string length");
	}
	return s;
    }
    
    public UUID readUUID() throws IOException {
        return new UUID(this.readLong(), this.readLong());
    }

    public long readLong() throws IOException {
	return this.dataIn.readLong();
    }

    public String readStringFromBuffer(int maxLength) throws IOException {
	int var2 = this.readVarInt();

	if (var2 > maxLength * 4) {
	    throw new RuntimeException("The received encoded string buffer length is longer than maximum allowed (" + var2 + " > " + maxLength * 4 + ")");
	} else if (var2 < 0) {
	    throw new RuntimeException("The received encoded string buffer length is less than zero! Weird string!");
	} else {
	    byte[] b = new byte[var2];
	    this.dataIn.readFully(b);
	    String var3 = new String(b, Charset.defaultCharset());

	    if (var3.length() > maxLength) {
		throw new RuntimeException("The received string length is longer than maximum allowed (" + var2 + " > " + maxLength + ")");
	    } else {
		return var3;
	    }
	}
    }

    public boolean readBoolean() throws IOException {
	return this.dataIn.readBoolean();
    }

    public int readUnsignedByte() throws IOException {
	return this.dataIn.readUnsignedByte();
    }

    public double readDouble() throws IOException {
	return this.dataIn.readDouble();
    }

    public int readInt() throws IOException {
	return this.dataIn.readInt();
    }

    public float readFloat() throws IOException {
	return this.dataIn.readFloat();
    }

    public long readVarLong() {
	long var1 = 0L;
	int var3 = 0;
	byte var4;

	do {
	    var4 = this.readByte();
	    var1 |= (long) (var4 & 127) << var3++ * 7;

	    if (var3 > 10) {
		throw new RuntimeException("VarLong too big");
	    }
	} while ((var4 & 128) == 128);

	return var1;
    }
}
