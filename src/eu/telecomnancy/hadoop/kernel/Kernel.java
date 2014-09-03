package eu.telecomnancy.hadoop.kernel;

import java.io.FileReader;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import com.google.gson.Gson;

import eu.telecomnancy.hadoop.kernel.conf.Conf;
import eu.telecomnancy.hadoop.server.Server;

public class Kernel {
	
	private static Conf conf;
	
	public static Executor executor;
	
	public static void main(String[] args) {
		
		System.setErr(System.out);
		Server server	= null;
		
		executor		= Executors.newFixedThreadPool(2);
		
		try {
			conf = (new Gson()).fromJson(new FileReader("conf/app.conf"), Conf.class);
			server = new Server(conf.getPort());
			server.start();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (server != null) {
				server.stop();
			}
		}
	}
	
	public static Conf conf() {
		return conf;
	}
}