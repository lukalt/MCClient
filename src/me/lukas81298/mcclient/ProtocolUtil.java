package me.lukas81298.mcclient;

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
}
