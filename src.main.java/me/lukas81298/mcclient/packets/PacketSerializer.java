package me.lukas81298.mcclient.packets;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class PacketSerializer {

    private DataOutputStream out;
    private ByteArrayOutputStream byteArrayOutputStream;

    public PacketSerializer(DataOutputStream out, ByteArrayOutputStream b) {
	this.out = out;
	this.byteArrayOutputStream = b;
    }

    
    public DataOutputStream getOut() {
        return out;
    }


    public ByteArrayOutputStream getByteArrayOutputStream() {
        return byteArrayOutputStream;
    }


    public void writeVarInt(int paramInt) throws IOException {
	while (true) {
	    if ((paramInt & 0xFFFFFF80) == 0) {
		out.writeByte(paramInt);
		return;
	    }

	    out.writeByte(paramInt & 0x7F | 0x80);
	    paramInt >>>= 7;
	}
    }

    public void writeByte(int b) {
	try {
	    this.out.writeByte(b);
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    public void writeUTF(String string) {
	try {
	    this.out.writeBytes(string);
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    public void writeShort(short s) {
	try {
	    this.out.writeShort((int) s);
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    public DataOutputStream getOutputStream() {
	return this.out;
    }
    
    public int getPacketSize() {
	return this.byteArrayOutputStream.size();
    }
    
}
