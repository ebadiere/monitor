package com.kopernik.monitor.uniswapV3;

import java.math.BigDecimal;
import java.math.BigInteger;
import lombok.extern.slf4j.Slf4j;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.tuples.generated.Tuple7;
import org.web3j.tx.ReadonlyTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.DefaultGasProvider;
import com.zuehlke.blockchain.model.IUniswapV3Pool;

@Slf4j
public class Quoter {

  String SHIB = "0x95aD61b0a150d79219dCF64E1E6Cc01f0B64C4cE";
  String WETH = "0xC02aaA39b223FE8D0A0e5C4F27eAD9083C756Cc2";
  String contractAddress = "0x94E4b2E24523CF9B3e631A6943C346dF9687c723";
  IUniswapV3Pool iUniswapV3Pool;

  public Quoter(Web3j web3j, Credentials credentials) {
    TransactionManager manager = new ReadonlyTransactionManager(web3j, "0xf39fd6e51aad88f6f4ce6ab8827279cfffb92266");
    iUniswapV3Pool = IUniswapV3Pool.load(contractAddress, web3j, manager, new DefaultGasProvider());
  }

  public String getQuoteForSinglePool() throws Exception {

    Tuple7<BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, Boolean> slot0 = iUniswapV3Pool.slot0().send();
    BigInteger sqrtRatioX96 = slot0.component1();
    BigInteger price = sqrtRatioX96.pow(2).divide(BigInteger.valueOf(2).pow(192));

    BigDecimal numerator = new BigDecimal(sqrtRatioX96.pow(2));
    BigDecimal denominator = new BigDecimal((BigInteger.valueOf(2).pow(192)));

    BigDecimal verifiedPrice = numerator.divide(denominator);

    log.info("Price: {}", verifiedPrice.toPlainString());


    return "tooMuch";
  }
}
