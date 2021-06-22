/*
VWAP Calculation: 

//5 shares at a 100 - BID
//3 shares at a 200 - BID
//2 shares at a 50 - OFFER
//10 shares for 1200  - OFFER

The 4 coloums would be: 
|Bid Price | Bid Amount | Offer Price | Offer Amount |
|   100    |     5      |    50       |      2       |
|   200    |     3      |    1200     |      10      |

There are 20 instruments (eg. Tata Company) and each instrument has 50 markets (LSE, BSE) that it gets traded on 

The VWAP two-way price for an instrument is defined as: 
Bid = Sum(Market Bid Price * Market Bid Amount)/ Sum(Market Bid Amount)  
Offer = Sum(Market Offer Price * Market Offer Amount)/ Sum(Market Offer Amount)

*/


package UBS;

public class CalculatorImp implements Calculator {
	

	public TwoWayPrice applyMarketUpdate(MarketUpdate twoWayMarketPrice) {
		
        TwoWayPrice price = twoWayMarketPrice.getTwoWayPrice();
		
		int market = twoWayMarketPrice.getMarket().getIndex();
		int instrument = price.getInstrument().getIndex();

	    VWAP vwap = vwaps[instrument];

		if (price.getBidAmount() > 0) {
			// update bid VWAP only if input contains bid amount
			// cater for cases where update is in fact One-Way or empty
			vwap.vwapbidsumNUM  += price.getBidAmount() * price.getBidPrice();
			vwap.vwapbidsumDENOM += price.getBidAmount();
		}
		if (price.getOfferAmount() > 0) {
			// update offer VWAP only if input contains offer amount
			// cater for cases where update is in fact One-Way or empty
			vwap.vwapoffersumNUM    += price.getOfferAmount() * price.getOfferPrice();
			vwap.vwapoffersumDENOM  += price.getOfferAmount();
		}

        vwaps[instrument] = vwap;
        
		return new TwoWayPriceImpl(
			twoWayMarketPrice.getTwoWayPrice().getInstrument(), 
			null, 
            //if in case any of the market prices had been indicative - the value being returned would be "INDICATIVE"
			computeVwapForBid (vwap),
			TotalBidAmount (vwap),
			computeVwapForOffer (vwap),
			TotalOfferAmount (vwap)
			);
	}

    double TotalBidAmount(VWAP vwap) {
		return vwap.vwapbidsumDENOM;
	}

	double computeVwapForBid(VWAP vwap) {
		return vwap.vwapbidsumNUM / vwap.vwapbidsumDENOM;
	}
	
	double TotalOfferAmount(VWAP vwap) {
		return vwap.vwapoffersumDENOM;
	}
	
	double computeVwapForOffer(VWAP vwap) {
		return vwap.vwapoffersumNUM / vwap.vwapoffersumDENOM;
	}

    private class VWAP {
		private double vwapbidsumNUM = 0;
		private double vwapbidsumDENOM = 0;
		private double vwapoffersumNUM = 0;
		private double vwapoffersumDENOM = 0;
	}

    private VWAP[] () fillarray{
		VWAP[][] vwaps = new VWAP[Instrument.SIZE];
		for (int instrument = 0; instrument< Instrument.SIZE; instrument++) {
			vwaps[instrument] = new VWAP();
		}
		
		return vwaps;
	}