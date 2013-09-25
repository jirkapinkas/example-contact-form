package cz.jiripinkas.example.listener;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import cz.jiripinkas.example.service.DatabaseService;

public class DatabaseCleanupListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		try {
			InputStream inputStream = getClass().getResourceAsStream("/email.properties");
			Properties properties = new Properties();
			properties.load(inputStream);
			inputStream.close();
			if ("true".equals(properties.getProperty("preview"))) {
				// if in preview, schedule database initialization once per day
				Timer timer = new Timer();
				timer.scheduleAtFixedRate(new TimerTask() {
					@Override
					public void run() {
						DatabaseService.init();
					}
				}, 0, 86400000);
			}
		} catch (IOException ex) {
			System.err.println("Could not initialize preview database");
			ex.printStackTrace();
		}
	}

}
