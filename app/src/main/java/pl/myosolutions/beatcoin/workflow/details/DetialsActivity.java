package pl.myosolutions.beatcoin.workflow.details;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import com.google.gson.Gson;

import pl.myosolutions.beatcoin.R;
import pl.myosolutions.beatcoin.databinding.ActivityDetialsBinding;
import pl.myosolutions.beatcoin.model.ExchangeItem;

import static pl.myosolutions.beatcoin.utils.CalculationUtils.calculateSpread;
import static pl.myosolutions.beatcoin.utils.DigitsUtils.formatWithDelimiters;
import static pl.myosolutions.beatcoin.workflow.IActivityTransitions.ITransitionsExtras.EXCHANGE_ITEM;


public class DetialsActivity extends AppCompatActivity {

    private static final String TAG = DetialsActivity.class.getSimpleName();

    private ActivityDetialsBinding binding;
    private ExchangeItem currentItem;


    public static Intent getStartingIntent(final Activity activity, final ExchangeItem exchangeItem) {
        if(exchangeItem == null) {
            return null;
        }

        Gson gson = new Gson();
        Intent intent = new Intent(activity, DetialsActivity.class);
        intent.putExtra(EXCHANGE_ITEM, gson.toJson(exchangeItem));
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detials);


        final Bundle args = getIntent().getExtras();
        if(args != null && args.containsKey(EXCHANGE_ITEM)) {
            Gson gson = new Gson();
            currentItem = gson.fromJson(args.getString(EXCHANGE_ITEM), ExchangeItem.class);
            Log.d(TAG, "currentItem: " + currentItem);
        }

        initToolbar();
        initViews();

    }

    private void initViews() {
        binding.filialDetails.coinAcronym.setText(currentItem.getBaseCurrency());
        binding.filialDetails.coinFullname.setText(currentItem.getBaseCurrencyName());
        binding.filialDetails.coinCurrentValue.setText(formatWithDelimiters(currentItem.getDetails().getLast()) + " " +  currentItem.getConversionCurrency());
        binding.filialDetails.coinHighValue.setText(formatWithDelimiters(currentItem.getDetails().getHigh()));
        binding.filialDetails.coinLowValue.setText(formatWithDelimiters(currentItem.getDetails().getLow()));
        binding.filialDetails.coinSpread.setText(formatWithDelimiters(calculateSpread(currentItem.getDetails().getBid(), currentItem.getDetails().getAsk())));
        binding.filialDetails.coinVolume.setText(currentItem.getDetails().getVolume());
    }

    private void initToolbar(){
        setSupportActionBar(binding.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
