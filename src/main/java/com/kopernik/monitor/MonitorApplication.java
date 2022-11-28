package com.kopernik.monitor;

import com.kopernik.monitor.config.MonitorProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.websocket.WebSocketService;

@PropertySource("classpath:application.yaml")
@SpringBootApplication
@Slf4j
public class MonitorApplication implements CommandLineRunner {

	String url;

	public MonitorApplication(MonitorProperties monitorProperties) {
		this.url = monitorProperties.getNetworkEndpoint();
	}

	public static void main(String[] args) {
		SpringApplication.run(MonitorApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("Running.... endpoint: {}", url);

		for (int i = 0; i < args.length; ++i) {
			log.info("args[{}]: {}", i, args[i]);
		}

		WebSocketService web3jService = new WebSocketService(url, true);
		web3jService.connect();
		Web3j web3j = Web3j.build(web3jService);

		// Subscribe to blocks
		web3j.blockFlowable(false).subscribe(block -> {
			log.info("NEW BLOCK -> " + block.getBlock().getNumber().intValue());
		});
	}
}
