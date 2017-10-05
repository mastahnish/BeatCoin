package pl.myosolutions.beatcoin.workflow.main;

import java.util.List;

import pl.myosolutions.beatcoin.model.ExchangeItem;

/**
 * Created by Jacek on 2017-09-27.
 */

public interface IMainActivity {

    interface Presenter {
        void getNewData(String currency);
    }

    interface View {
        void propagateServerResponse(List<ExchangeItem> response);
        android.view.View getRootView();
    }

}
