package com.kopernik.monitor;

import com.kopernik.monitor.config.MonitorProperties;
import com.kopernik.monitor.uniswapV3.Pool;
import java.math.BigInteger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.EthGasPrice;
import org.web3j.protocol.http.HttpService;

@PropertySource("classpath:application.yaml")
@SpringBootApplication
@Slf4j
public class MonitorApplication implements CommandLineRunner, ExitCodeGenerator {

	String contractAddress;
	Pool pool = null;
	String privateKey;
	String publicKey;
	String url;

	public MonitorApplication(MonitorProperties monitorProperties) {
		this.contractAddress = "0x" + monitorProperties.getPoolAddress();
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
		EthGasPrice ethGasPrice = web3j.ethGasPrice().send();
		log.info("Gas Price: {}", ethGasPrice.getGasPrice());
//		web3jService.connect();
//		Web3j web3j = Web3j.build(web3jService);

		if (args[0].equals("quote")){
			log.info("Getting quote");
			Credentials credentials = Credentials.create(privateKey);
			this.pool = new Pool(web3j, credentials, this.contractAddress);
			String poolAddress = this.pool.getPoolAddress("0x6B175474E89094C44Da98b954EedeAC495271d0F",
					"0xC02aaA39b223FE8D0A0e5C4F27eAD9083C756Cc2",
					BigInteger.valueOf(3000));
			log.info("Pool address: {}", poolAddress);
			this.pool.token0Price();
			this.pool.token1Price();
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
