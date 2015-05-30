package me.lukas81298.mcclient;

public class Settings {

    private String username, server;

    private int port;

    public Settings(String username, String server, int port) {
	super();
	this.username = username;
	this.server = server;
	this.port = port;
    }

    public String getUsername() {
	return username;
    }

    public void setUsername(String username) {
	this.username = username;
    }

    public String getServer() {
	return server;
    }

    public void setServer(String server) {
	this.server = server;
    }

    public int getPort() {
	return port;
    }

    public void setPort(int port) {
	this.port = port;
    }

}
