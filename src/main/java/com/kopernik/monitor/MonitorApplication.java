package com.kopernik.monitor;

import com.kopernik.monitor.config.MonitorProperties;
import com.kopernik.monitor.uniswapV3.Quoter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.protocol.websocket.WebSocketService;
import org.web3j.tx.gas.DefaultGasProvider;

@PropertySource("classpath:application.yaml")
@SpringBootApplication
@Slf4j
public class MonitorApplication implements CommandLineRunner, ExitCodeGenerator {

	Quoter quoter = null;
	String privateKey;
	String publicKey;
	String url;

	public MonitorApplication(MonitorProperties monitorProperties) {
		this.publicKey = "0x" + monitorProperties.getPublicKey();
		this.privateKey = monitorProperties.getPrivateKey();
		this.url = monitorProperties.getNetworkEndpoint();
	}

	public static void main(String[] args) {
		System.exit(SpringApplication.exit(SpringApplication.run(MonitorApplication.class, args)));
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("Running.... endpoint: {}", url);

		for (int i = 0; i < args.length; ++i) {
			log.info("args[{}]: {}", i, args[i]);
		}


//		WebSocketService web3jService = new WebSocketService(url, true);
		Web3j web3j = Web3j.build(new HttpService());
//		web3jService.connect();
//		Web3j web3j = Web3j.build(web3jService);

		if (args[0].equals("quote")){
			log.info("Getting quote");
			Credentials credentials = Credentials.create(privateKey);
			this.quoter = new Quoter(web3j, credentials);
			this.quoter.getQuoteForSinglePool();
		}

		// Subscribe to blocks
//		web3j.blockFlowable(false).subscribe(block -> {
//			log.info("NEW BLOCK -> " + block.getBlock().getNumber().intValue());
//		});
	}

	@Override
	public int getExitCode() {
		return 0;
	}
}
