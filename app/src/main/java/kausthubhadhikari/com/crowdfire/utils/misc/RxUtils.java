package kausthubhadhikari.com.crowdfire.utils.misc;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by kausthubhadhikari on 21/12/16.
 */

public class RxUtils {

    public <T> Observable.Transformer<T, T> applySchedulers() {
        return tObservable -> tObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
