package com.kopernik.monitor.uniswapV3;

import java.math.BigInteger;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import com.zuehlke.blockchain.model.IQuoter;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.gas.DefaultGasProvider;

public class Quoter {

  String SHIB = "0x95aD61b0a150d79219dCF64E1E6Cc01f0B64C4cE";
  String WETH = "0xC02aaA39b223FE8D0A0e5C4F27eAD9083C756Cc2";
  String contractAddress = "0xb27308f9F90D607463bb33eA1BeBb41C27CE5AB6";
  IQuoter iQuoter;

  public Quoter(Web3j web3j, Credentials credentials) {
    iQuoter = IQuoter.load(contractAddress, web3j, credentials, new DefaultGasProvider());
  }

  public String getQuoteForSinglePool() throws Exception {

    TransactionReceipt receipt = iQuoter.quoteExactInputSingle(SHIB, WETH, BigInteger.valueOf(3000), BigInteger.valueOf(10000), BigInteger.valueOf(0)).send();

    return "tooMuch";
  }
}
