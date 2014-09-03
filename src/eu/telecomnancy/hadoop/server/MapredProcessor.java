package eu.telecomnancy.hadoop.server;

import java.io.File;
import java.io.IOException;

public class MapredProcessor implements Runnable {

	private String line;

	public MapredProcessor(String line) {
		this.line = line;
	}

	@Override
	public void run() {
		synchronized (this) {
			
			String[] parts		= line.split(" ");
			String year			= parts[0];
			String pid			= parts[1];
			
			Process p;
			try {
				System.out.println(String.format("Processing %s/%s", year, pid));
				p = new ProcessBuilder("hadoop", "jar", "/home/hduser/hadoop-server/mapred-storage.jar", year, pid, "172.16.67.132", "27017", "factapgx")
						.redirectErrorStream(true)
						.redirectOutput(new File(String.format("process/process-%s-%s.out", year, pid)))
						.start();
				
				p.waitFor();
				System.out.println(String.format("Processed %s/%s", year, pid));
			} catch (IOException | InterruptedException e) {
				e.printStackTrace();
			}
		
		}
	}

}
