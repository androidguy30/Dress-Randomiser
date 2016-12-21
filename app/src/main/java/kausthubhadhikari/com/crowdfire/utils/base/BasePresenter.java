package kausthubhadhikari.com.crowdfire.utils.base;

import android.support.annotation.CallSuper;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by kausthubhadhikari on 20/12/16.
 */

public abstract class BasePresenter {

    private final BaseView view;
    private final CompositeSubscription compositeSubscription;

    public BasePresenter(BaseView baseView) {
        this.view = baseView;
        compositeSubscription = new CompositeSubscription();
    }

    @CallSuper
    protected void unsubcribe() {
        compositeSubscription.unsubscribe();
    }

    protected void addSubsciption(Subscription subscription) {
        compositeSubscription.add(subscription);
    }

    public abstract void onViewCreated(boolean isLaunched);


}
