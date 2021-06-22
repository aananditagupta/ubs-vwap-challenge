package UBS;

public class CalculatorImp implements Calculator {
	
	public TwoWayPrice applyMarketUpdate(MarketUpdate twoWayMarketPrice) {
		
        TwoWayPrice price = twoWayMarketPrice.getTwoWayPrice();
		
		int market = twoWayMarketPrice.getMarket().getIndex();
		int instrument = price.getInstrument().getIndex();
		
		VwapAsFraction vwap = vwaps[market][instrument];

		if (price.getBidAmount() > 0) {
			// update bid VWAP only if input contains bid amount
			// cater for cases where update is in fact One-Way or empty
			int vwapbidsumNUM  += price.getBidAmount() * price.getBidPrice();
			int vwapbidsumDENOM += price.getBidAmount();
		}
		if (price.getOfferAmount() > 0) {
			// update offer VWAP only if input contains offer amount
			// cater for cases where update is in fact One-Way or empty
			int vwapoffersumNUM    += price.getOfferAmount() * price.getOfferPrice();
			int vwapoffersumDENOM  += price.getOfferAmount();
		}
		vwaps[market][instrument] = vwap;
		
		return new TwoWayPriceImpl(
			twoWayMarketPrice.getTwoWayPrice().getInstrument(), 
			null,
			computeVwapForBid(vwap),
			getTotalBidAmount(vwap),
			computeVwapForOffer(vwap),
			getTotalOfferAmount(vwap)
			);
	}