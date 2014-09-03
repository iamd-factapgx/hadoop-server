package eu.telecomnancy.hadoop.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import eu.telecomnancy.hadoop.kernel.Kernel;

class ConnectionHandler extends Thread {
	private Socket socket;

	public ConnectionHandler(Socket socket) {
		this.socket		= socket;
	}
	
	public void run() {
		try {
			BufferedReader reader	= new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String line				= reader.readLine();
			
			try {
				Thread t = new Thread(new MapredProcessor(line));
				reader.close();
				socket.close();
				Kernel.executor.execute(t);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}