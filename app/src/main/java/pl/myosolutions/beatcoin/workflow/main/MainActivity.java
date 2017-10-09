package pl.myosolutions.beatcoin.workflow.main;

import android.app.SearchManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.SearchView;

import com.crashlytics.android.Crashlytics;

import java.util.List;

import io.fabric.sdk.android.Fabric;
import pl.myosolutions.beatcoin.R;
import pl.myosolutions.beatcoin.databinding.ActivityMainBinding;
import pl.myosolutions.beatcoin.model.ExchangeItem;
import pl.myosolutions.beatcoin.workflow.IActivityTransitions;
import pl.myosolutions.beatcoin.workflow.details.DetialsActivity;
import pl.myosolutions.beatcoin.workflow.main.list.ExchangeAdapter;
import pl.myosolutions.beatcoin.workflow.main.list.IExchangeValues;

public class MainActivity extends AppCompatActivity implements IMainActivity.View, ExchangeAdapter.OnItemClickListener, SearchView.OnQueryTextListener {


    private static final String TAG = MainActivity.class.getSimpleName();
    private ActivityMainBinding binding;
    private ExchangeAdapter adapter;
    private MainActivityPresenterImpl mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        mPresenter = new MainActivityPresenterImpl(this);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        initViews();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.getNewData(IExchangeValues.Currencies.PLN);
    }

    private void initViews() {
        adapter = new ExchangeAdapter(this);
        binding.rvExchangeList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        binding.rvExchangeList.setAdapter(adapter);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        ComponentName componentName = new ComponentName(this, MainActivity.class);


        binding.searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName));
        binding.searchView.setQueryHint(getString(R.string.search_hint));
        binding.searchView.setOnQueryTextListener(this);
        binding.searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.searchView.setIconified(false);
            }
        });
    }

    @Override
    public void propagateServerResponse(List<ExchangeItem> response) {
        Log.d(TAG, "propagateServerResponse: " + response.toString());
        adapter.refreshData(response);
    }

    @Override
    public View getRootView() {
        return binding.getRoot();
    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        return updateAdapter(query);
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return updateAdapter(newText);
    }

    private boolean updateAdapter(String query){
        if(adapter ==null) {
            return false;
        }
        List<ExchangeItem> list = mPresenter.getFilteredList(query);
        adapter.refreshData(list);

        return true;
    }

    @Override
    public void onItemClick(ExchangeItem item) {

        startActivityForResult(DetialsActivity.getStartingIntent(this, item), IActivityTransitions.MAIN_TO_DETAILS);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode== RESULT_OK){
            if(requestCode == IActivityTransitions.MAIN_TO_DETAILS){
                //do something
            }
        }
    }
}
