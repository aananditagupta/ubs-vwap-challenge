# Calculate the Volume Weighted Average Price (VWAP) 

Task: Design and write an implementation of the Calculator interface below so on the applyMarketUpdate method being called the TwoWayPrice returned is the VWAP (volume weighted average price) two-way price for the instrument of the MarketUpdate. Each instrument can receive a two way price update from one of 50 markets, and in calculating the VWAP for the instrument the calculator should consider the most recent price update for each market (received so far)  

<ul> 
<li> From the document I was not sure if the VWAP is meant to be calculated on market (ie Stock Exchange) and instrument basis (ie Company or similar) or only instrument and aggregate for all markets. Test suggests calculate market separately, so I have gone with this assumption.

<li> The State (FIRM/INDICATIVE) coding has been ignored for now. The logic however for the implementation would be: 


