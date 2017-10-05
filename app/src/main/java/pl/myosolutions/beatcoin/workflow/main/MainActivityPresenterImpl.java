package pl.myosolutions.beatcoin.workflow.main;

import android.app.Activity;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;

import java.util.List;
import java.util.Map;

import pl.myosolutions.beatcoin.http.TickerService;
import pl.myosolutions.beatcoin.http.TickerServiceFactory;
import pl.myosolutions.beatcoin.model.ExchangeItem;
import pl.myosolutions.beatcoin.model.ExchangeItemDetails;
import pl.myosolutions.beatcoin.workflow.main.list.ResponseHelper;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static pl.myosolutions.beatcoin.http.IEndpointRequests.TICKER_JSON;

/**
 * Created by Jacek on 2017-09-27.
 */

public class MainActivityPresenterImpl implements IMainActivity.Presenter{


    private static final String TAG = MainActivityPresenterImpl.class.getSimpleName();
    private Activity activity;
    private IMainActivity.View mView;
    private List<ExchangeItem> currentList;

    public MainActivityPresenterImpl(Activity ac) {
        this.activity = ac;
        this.mView = (IMainActivity.View) ac;

    }

    @Override
    public void getNewData(final String currency) {
        TickerService service = TickerServiceFactory.createRetrofitService(TickerService.class, TickerService.SERVICE_ENDPOINT);
        service.getTickerData(TICKER_JSON)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Map<String, ExchangeItemDetails>>() {
                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        final Snackbar snackbar = Snackbar.make(mView.getRootView(), "Couldn't connect to server", Snackbar.LENGTH_INDEFINITE);

                        snackbar.setAction("Retry", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                getNewData(currency);
                                snackbar.dismiss();
                            }
                        }).show();
                        Log.d(TAG, "onError: " + e.getMessage());
                    }

                    @Override
                    public void onNext(Map<String, ExchangeItemDetails> exchangeItemMap) {
                        Log.d(TAG, "onNext: " + exchangeItemMap.toString());
                        List<ExchangeItem> listFromResponse = ResponseHelper.convertResponseToExchangeItemlist(exchangeItemMap);
                        List<ExchangeItem> filteredListFromResponse = ResponseHelper.getListFilteredByConversionCurrency(listFromResponse, currency);
                        setCurrentList(filteredListFromResponse);

                        mView.propagateServerResponse(filteredListFromResponse);
                    }
                });
    }

    public  List<ExchangeItem> getFilteredList(String query){
        return ResponseHelper.getListFilteredByBaseCurrency(getCurrentList(), query);
    }

    public List<ExchangeItem> getCurrentList() {
        return currentList;
    }

    public void setCurrentList(List<ExchangeItem> currentList) {
        this.currentList = currentList;
    }
}
