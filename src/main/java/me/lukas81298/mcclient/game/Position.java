package me.lukas81298.mcclient.game;

public class Position {

    private int x, y, z;

    public Position(int x, int y, int z) {
	super();
	this.x = x;
	this.y = y;
	this.z = z;
    }

    public int getX() {
	return x;
    }

    public void setX(int x) {
	this.x = x;
    }

    public int getY() {
	return y;
    }

    public void setY(int y) {
	this.y = y;
    }

    public int getZ() {
	return z;
    }

    public void setZ(int z) {
	this.z = z;
    }

    public static Position fromLong(long val) {
	int var2 = (int) val >> 38;
	int var3 = (int) (val >> 26) & 0xFFF;
	int var4 = (int) val << 38 >> 38;;
	return new Position(var2, var3, var4);
    }

}
