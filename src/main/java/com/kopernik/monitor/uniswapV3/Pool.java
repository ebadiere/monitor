package com.kopernik.monitor.uniswapV3;

import com.zuehlke.blockchain.model.IUniswapV3Factory;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.tuples.generated.Tuple7;
import org.web3j.tx.ReadonlyTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.DefaultGasProvider;
import com.zuehlke.blockchain.model.IUniswapV3Pool;

@Slf4j
public class Pool {

  IUniswapV3Factory iUniswapV3Factory;
  IUniswapV3Pool iUniswapV3Pool;

  String FACTORY_ADDRESS = "0x1F98431c8aD98523631AE4a59f267346ea31F984";

  public Pool(Web3j web3j, Credentials credentials, String contractAddress) {
    TransactionManager manager = new ReadonlyTransactionManager(web3j, credentials.getAddress());
    iUniswapV3Pool = IUniswapV3Pool.load(contractAddress, web3j, manager, new DefaultGasProvider());
    iUniswapV3Factory = IUniswapV3Factory.load(FACTORY_ADDRESS, web3j, manager, new DefaultGasProvider());
  }

  public String getPoolAddress(String token1Address, String token2Address, BigInteger fee) throws Exception {
    return iUniswapV3Factory.getPool(token1Address, token2Address, fee).send();
  }

  public BigDecimal token0Price() throws Exception {

    BigInteger sqrtRatioX96 = getSqrtRatioX96();

    BigDecimal numerator = new BigDecimal(sqrtRatioX96.pow(2));
    BigDecimal denominator = new BigDecimal((BigInteger.valueOf(2).pow(192)));

    return getPrice(numerator, denominator);
  }

  public BigDecimal token1Price() throws Exception {
    BigInteger sqrtRatioX96 = getSqrtRatioX96();

    BigDecimal numerator = new BigDecimal((BigInteger.valueOf(2).pow(192)));
    BigDecimal denominator = new BigDecimal(sqrtRatioX96.pow(2));

    return getPrice(numerator, denominator);
  }

  private BigInteger getSqrtRatioX96() throws Exception {
    Tuple7<BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, BigInteger, Boolean> slot0 = iUniswapV3Pool.slot0().send();
    return slot0.component1();
  }

  @NotNull
  private BigDecimal getPrice(BigDecimal numerator, BigDecimal denominator) {

    BigDecimal price = numerator.divide(denominator, 18, RoundingMode.HALF_UP);

    log.info("Price: {}", price.toPlainString());

    return price;
  }

}
