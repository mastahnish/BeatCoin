package pl.myosolutions.coinbe.http;

import retrofit.RestAdapter;

/**
 * Created by Jacek on 2017-09-27.
 */

public class TickerServiceFactory {

    public static <T> T createRetrofitService(final Class<T> clazz, final String endPoint) {
        final RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(endPoint)
                .build();
        T service = restAdapter.create(clazz);

        return service;
    }
}
