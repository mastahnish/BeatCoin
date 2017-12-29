package pl.myosolutions.coinbe.workflow.details;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import com.google.gson.Gson;

import pl.myosolutions.coinbe.R;
import pl.myosolutions.coinbe.databinding.ActivityDetialsBinding;
import pl.myosolutions.coinbe.model.ExchangeItem;
import pl.myosolutions.coinbe.utils.DrawableUtils;

import static pl.myosolutions.coinbe.workflow.IActivityTransitions.ITransitionsExtras.EXCHANGE_ITEM;


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
        binding.setExchangeItem(currentItem);

        if(currentItem.getIcon() == -1){
            DrawableUtils.drawDrawableToImageView(this, currentItem.getBaseCurrency(), binding.filialDetails.coinIcon);
        }

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
