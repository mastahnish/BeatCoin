package pl.myosolutions.beatcoin.workflow;

/**
 * Created by Jacek on 2017-10-03.
 */

public interface IActivityTransitions {



    int MAIN_TO_DETAILS = 1;

    interface ITransitionsExtras{
        String EXCHANGE_ITEM = "exchange_item_key";
    }


}
