package eu.telecomnancy.hadoop.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	private int port;
	private ServerSocket server;
	private boolean running;

	public Server(int port) {
		this.port = port;
	}
	

	public void start() throws IOException {
		server		= new ServerSocket(port);
		running		= true;
		
		System.out.println(String.format("Server running at %s:%s", server.getInetAddress().getHostAddress(), port));
		
		while (running) {
			Socket connection = server.accept();
			(new ConnectionHandler(connection)).start();
		}
	}
	
	public void stop() {
		try {
			if (server != null) {
				server.close();
			}
			running = false;
		} catch(IOException e) { e.printStackTrace(); }
	}
}
