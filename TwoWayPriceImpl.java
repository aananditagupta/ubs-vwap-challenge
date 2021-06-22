/*

TASK: Design and write an implementation of the Calculator interface below so 
on the applyMarketUpdate method being called 
the TwoWayPrice returned is the VWAP (volume weighted average price) two-way price 
for the instrument of the MarketUpdate. 
Each instrument can receive a two way price update from one of 50 markets, 
and in calculating the VWAP for the instrument the calculator should consider the most recent price update for each market (received so far)

VWAP Calculation: 

//5 shares at a 100 
//3 shares at a 200 
//2 shares at a 50
//10 shares for 1200 

Bid Price | Bid Amount | Offer Price | Offer Amount |

The VWAP two-way price for an instrument is defined as: 
Bid = Sum(Market Bid Price * Market Bid Amount)/ Sum(Market Bid Amount)  
Offer = Sum(Market Offer Price * Market Offer Amount)/ Sum(Market Offer Amount)

*/

package UBS;

public class TwoWayPriceImpl implements TwoWayPrice {
	private final Instrument instrument;
	private final State state;
	private final double bidPrice;
	private final double offerAmount;
	private final double offerPrice;
	private final double bidAmount;
	
	public TwoWayPriceImpl( Instrument instrument, State state, double bidPrice, double bidAmount, double offerPrice, double offerAmount ) {
		this.instrument = instrument;
		this.state = state;
		this.bidPrice = bidPrice;
		this.offerAmount = offerAmount;
		this.offerPrice = offerPrice;
		this.bidAmount = bidAmount;
	}

	public Instrument getInstrument() {
		return instrument;
	}

	public State getState() {
		return state;
	}

	public double getBidPrice() {
		return bidPrice;
	}

	public double getOfferAmount() {
		return offerAmount;
	}

	public double getOfferPrice() {
		return offerPrice;
	}

	public double getBidAmount() {
		return bidAmount;
	}
}
