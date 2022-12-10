package com.zuehlke.blockchain.model;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Int24;
import org.web3j.abi.datatypes.generated.Uint24;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 4.9.4.
 */
public class IUniswapV3Factory extends Contract {
    public static final String BINARY = "";

    public static final String FUNC_CREATEPOOL = "createPool";

    public static final String FUNC_ENABLEFEEAMOUNT = "enableFeeAmount";

    public static final String FUNC_FEEAMOUNTTICKSPACING = "feeAmountTickSpacing";

    public static final String FUNC_GETPOOL = "getPool";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_SETOWNER = "setOwner";

    public static final Event FEEAMOUNTENABLED_EVENT = new Event("FeeAmountEnabled", 
            Arrays.asList(new TypeReference<Uint24>(true) {}, new TypeReference<Int24>(true) {}));

    public static final Event OWNERCHANGED_EVENT = new Event("OwnerChanged", 
            Arrays.asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}));

    public static final Event POOLCREATED_EVENT = new Event("PoolCreated", 
            Arrays.asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint24>(true) {}, new TypeReference<Int24>() {}, new TypeReference<Address>() {}));

    @Deprecated
    protected IUniswapV3Factory(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected IUniswapV3Factory(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected IUniswapV3Factory(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected IUniswapV3Factory(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static List<FeeAmountEnabledEventResponse> getFeeAmountEnabledEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(FEEAMOUNTENABLED_EVENT, transactionReceipt);
        ArrayList<FeeAmountEnabledEventResponse> responses = new ArrayList<>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            FeeAmountEnabledEventResponse typedResponse = new FeeAmountEnabledEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.fee = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.tickSpacing = (BigInteger) eventValues.getIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<FeeAmountEnabledEventResponse> feeAmountEnabledEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> {
            EventValuesWithLog eventValues = extractEventParametersWithLog(FEEAMOUNTENABLED_EVENT, log);
            FeeAmountEnabledEventResponse typedResponse = new FeeAmountEnabledEventResponse();
            typedResponse.log = log;
            typedResponse.fee = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.tickSpacing = (BigInteger) eventValues.getIndexedValues().get(1).getValue();
            return typedResponse;
        });
    }

    public Flowable<FeeAmountEnabledEventResponse> feeAmountEnabledEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(FEEAMOUNTENABLED_EVENT));
        return feeAmountEnabledEventFlowable(filter);
    }

    public static List<OwnerChangedEventResponse> getOwnerChangedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(OWNERCHANGED_EVENT, transactionReceipt);
        ArrayList<OwnerChangedEventResponse> responses = new ArrayList<>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            OwnerChangedEventResponse typedResponse = new OwnerChangedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.oldOwner = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.newOwner = (String) eventValues.getIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<OwnerChangedEventResponse> ownerChangedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> {
            EventValuesWithLog eventValues = extractEventParametersWithLog(OWNERCHANGED_EVENT, log);
            OwnerChangedEventResponse typedResponse = new OwnerChangedEventResponse();
            typedResponse.log = log;
            typedResponse.oldOwner = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.newOwner = (String) eventValues.getIndexedValues().get(1).getValue();
            return typedResponse;
        });
    }

    public Flowable<OwnerChangedEventResponse> ownerChangedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(OWNERCHANGED_EVENT));
        return ownerChangedEventFlowable(filter);
    }

    public static List<PoolCreatedEventResponse> getPoolCreatedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(POOLCREATED_EVENT, transactionReceipt);
        ArrayList<PoolCreatedEventResponse> responses = new ArrayList<>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            PoolCreatedEventResponse typedResponse = new PoolCreatedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.token0 = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.token1 = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.fee = (BigInteger) eventValues.getIndexedValues().get(2).getValue();
            typedResponse.tickSpacing = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.pool = (String) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<PoolCreatedEventResponse> poolCreatedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> {
            EventValuesWithLog eventValues = extractEventParametersWithLog(POOLCREATED_EVENT, log);
            PoolCreatedEventResponse typedResponse = new PoolCreatedEventResponse();
            typedResponse.log = log;
            typedResponse.token0 = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.token1 = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.fee = (BigInteger) eventValues.getIndexedValues().get(2).getValue();
            typedResponse.tickSpacing = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.pool = (String) eventValues.getNonIndexedValues().get(1).getValue();
            return typedResponse;
        });
    }

    public Flowable<PoolCreatedEventResponse> poolCreatedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(POOLCREATED_EVENT));
        return poolCreatedEventFlowable(filter);
    }

    public RemoteFunctionCall<TransactionReceipt> createPool(String tokenA, String tokenB, BigInteger fee) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_CREATEPOOL, 
                Arrays.asList(new org.web3j.abi.datatypes.Address(160, tokenA),
                new org.web3j.abi.datatypes.Address(160, tokenB), 
                new org.web3j.abi.datatypes.generated.Uint24(fee)), 
                Collections.emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> enableFeeAmount(BigInteger fee, BigInteger tickSpacing) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_ENABLEFEEAMOUNT, 
                Arrays.asList(new org.web3j.abi.datatypes.generated.Uint24(fee),
                new org.web3j.abi.datatypes.generated.Int24(tickSpacing)), 
                Collections.emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> feeAmountTickSpacing(BigInteger fee) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_FEEAMOUNTTICKSPACING,
            List.of(new Uint24(fee)),
            List.of(new TypeReference<Int24>() {
            }));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<String> getPool(String tokenA, String tokenB, BigInteger fee) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETPOOL, 
                Arrays.asList(new org.web3j.abi.datatypes.Address(160, tokenA),
                new org.web3j.abi.datatypes.Address(160, tokenB), 
                new org.web3j.abi.datatypes.generated.Uint24(fee)),
            List.of(new TypeReference<Address>() {
            }));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<String> owner() {
        final org.web3j.abi.datatypes.Function function;
        function = new org.web3j.abi.datatypes.Function(FUNC_OWNER,
            List.of(),
            List.of(new TypeReference<Address>() {
            }));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> setOwner(String _owner) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETOWNER,
                List.of(new Address(160, _owner)),
                Collections.emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static IUniswapV3Factory load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new IUniswapV3Factory(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static IUniswapV3Factory load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new IUniswapV3Factory(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static IUniswapV3Factory load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new IUniswapV3Factory(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static IUniswapV3Factory load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new IUniswapV3Factory(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<IUniswapV3Factory> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(IUniswapV3Factory.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<IUniswapV3Factory> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(IUniswapV3Factory.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<IUniswapV3Factory> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(IUniswapV3Factory.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<IUniswapV3Factory> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(IUniswapV3Factory.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class FeeAmountEnabledEventResponse extends BaseEventResponse {
        public BigInteger fee;

        public BigInteger tickSpacing;
    }

    public static class OwnerChangedEventResponse extends BaseEventResponse {
        public String oldOwner;

        public String newOwner;
    }

    public static class PoolCreatedEventResponse extends BaseEventResponse {
        public String token0;

        public String token1;

        public BigInteger fee;

        public BigInteger tickSpacing;

        public String pool;
    }
}
