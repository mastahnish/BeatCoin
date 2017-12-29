package pl.myosolutions.coinbe.workflow.main;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import pl.myosolutions.coinbe.R;
import pl.myosolutions.coinbe.http.TickerService;
import pl.myosolutions.coinbe.http.TickerServiceFactory;
import pl.myosolutions.coinbe.model.ExchangeItem;
import pl.myosolutions.coinbe.model.ExchangeItemDetails;
import pl.myosolutions.coinbe.workflow.main.list.ResponseHelper;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static pl.myosolutions.coinbe.http.IEndpointRequests.TICKER_JSON;
import static pl.myosolutions.coinbe.workflow.IActivityKeys.CURRENT_LIST_TOTAL_KEY;

/**
 * Created by Jacek on 2017-09-27.
 */

public class MainActivityPresenterImpl implements IMainActivity.Presenter{


    private static final String TAG = MainActivityPresenterImpl.class.getSimpleName();
    private Activity activity;
    private IMainActivity.View mView;
    private List<ExchangeItem> currentListTotal;
    private String currentMarket;
    private List<ExchangeItem> currentListMarket;

    public MainActivityPresenterImpl(Activity ac) {
        this.activity = ac;
        this.mView = (IMainActivity.View) ac;

    }

    @Override
    public void getNewData() {
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
                        mView.setSwipeToRefreshVisibility(false);

                        final Snackbar snackbar = Snackbar.make(mView.getRootView(), R.string.no_internet, Snackbar.LENGTH_INDEFINITE);

                        snackbar.setAction(R.string.retry, new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                getNewData();
                                snackbar.dismiss();
                            }
                        }).show();

                        mView.setConnectionSnackbar(snackbar);

                        Log.d(TAG, "onError: " + e.getMessage());


                    }

                    @Override
                    public void onNext(Map<String, ExchangeItemDetails> exchangeItemMap) {
                        mView.setSwipeToRefreshVisibility(false);
                        if (mView.getConnectionSnackbar()!=null && mView.getConnectionSnackbar().isShown()) mView.getConnectionSnackbar().dismiss();

                        Log.d(TAG, "onNext: " + exchangeItemMap.toString());
                        List<ExchangeItem> listFromResponse = ResponseHelper.convertResponseToExchangeItemlist(exchangeItemMap);
                        setCurrentListTotal(listFromResponse);
                        List<ExchangeItem> filteredListFromResponse = ResponseHelper.getListFilteredByConversionCurrency(listFromResponse, currentMarket);
                        setCurrentListMarket(filteredListFromResponse);

                        mView.propagateServerResponse(filteredListFromResponse);
                    }
                });
    }


    @Override
    public void setupDataForMarket() {
        List<ExchangeItem> filteredListFromResponse = ResponseHelper.getListFilteredByConversionCurrency(getCurrentListTotal(), getCurrentMarket());
        setCurrentListMarket(filteredListFromResponse);
        mView.propagateServerResponse(filteredListFromResponse);
    }

    public void setCurrentListMarket(List<ExchangeItem> currentListMarket) {
        this.currentListMarket = currentListMarket;
    }

    public List<ExchangeItem> getCurrentListMarket() {
        return currentListMarket;
    }

    public  List<ExchangeItem> getFilteredList(String query){
        return ResponseHelper.getListFilteredByBaseCurrency(getCurrentListMarket(), query);
    }

    public List<ExchangeItem> getCurrentListTotal() {
        return currentListTotal;
    }

    public void setCurrentListTotal(List<ExchangeItem> currentListTotal) {
        this.currentListTotal = currentListTotal;
    }

    public String getCurrentMarket() {
        return currentMarket;
    }

    public void setCurrentMarket(String currentMarket) {
        this.currentMarket = currentMarket;
    }

    public void setCurrentListsAfterRecreation(Bundle savedInstanceState) {
        if(savedInstanceState!=null && savedInstanceState.containsKey(CURRENT_LIST_TOTAL_KEY)){
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<ExchangeItem>>(){}.getType();

            setCurrentListTotal((List<ExchangeItem>) gson.fromJson(savedInstanceState.getString(CURRENT_LIST_TOTAL_KEY),listType));
            setupDataForMarket();
        }
    }
}
