# Monitor

Monitor part of a two part project.  The intent of this project is to monitor a set of UniswapV3 pools
and re-balance the pools if there is an arbitrage opportunity.  It uses the Web3j library.

It will call the solidity contracts in the arbitrage project.

#Possible future enhancements
As it consumes some UniswapV3 contracts this could be enhanced to become a full java UniswapV3 SDK.

#Current WIP
Building out the price Quote for a given pool.

To run:
mvn spring-boot:run -Dspring-boot.run.arguments="args1,args2"