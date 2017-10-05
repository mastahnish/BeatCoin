package pl.myosolutions.beatcoin.http;

/**
 * Created by Jacek on 2017-09-26.
 */

public interface IEndpointRequests {

    String SEPARATOR = "/";
    String TICKER = "ticker";
    String TICKER_JSON = "ticker.json";

    String TICKER_URL = SEPARATOR +  TICKER + SEPARATOR;

}
