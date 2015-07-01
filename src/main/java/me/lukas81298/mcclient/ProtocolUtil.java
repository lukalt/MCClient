package me.lukas81298.mcclient;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class ProtocolUtil {

    public static final int version = 47;

    public static void writeVarInt(DataOutputStream out, int paramInt) throws IOException {
	while (true) {
	    if ((paramInt & 0xFFFFFF80) == 0) {
		out.writeByte(paramInt);
		return;
	    }

	    out.writeByte(paramInt & 0x7F | 0x80);
	    paramInt >>>= 7;
	}
    }

    public static int getVarIntSize(int input) {
	for (int var1 = 1; var1 < 5; ++var1) {
	    if ((input & -1 << var1 * 7) == 0) {
		return var1;
	    }
	}

	return 5;
    }

    public static int readVarInt(DataInputStream in) throws IOException {
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

}
