package pl.myosolutions.coinbe.http;

import java.util.Map;

import pl.myosolutions.coinbe.model.ExchangeItemDetails;
import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

/**
 * Created by Jacek on 2017-09-26.
 */

public interface TickerService {

    String SERVICE_ENDPOINT = "https://coinbe.net/public/graphs/";

    @GET(IEndpointRequests.TICKER_URL + "{file}")
    Observable<Map<String, ExchangeItemDetails>> getTickerData(@Path("file") String file);
}
