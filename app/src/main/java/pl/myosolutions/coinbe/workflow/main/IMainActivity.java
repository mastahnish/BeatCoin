package pl.myosolutions.coinbe.workflow.main;

import android.support.design.widget.Snackbar;

import java.util.List;

import pl.myosolutions.coinbe.model.ExchangeItem;

/**
 * Created by Jacek on 2017-09-27.
 */

public interface IMainActivity {

    interface Presenter {
        void getNewData();
        void setupDataForMarket();
    }

    interface View {
        void propagateServerResponse(List<ExchangeItem> response);
        android.view.View getRootView();
        void setSwipeToRefreshVisibility(boolean visibility);
        void setConnectionSnackbar(Snackbar snackbar);
        Snackbar getConnectionSnackbar();
    }

}
