package kausthubhadhikari.com.crowdfire.model.manager;

import java.util.ArrayList;

import kausthubhadhikari.com.crowdfire.model.data.Database;
import kausthubhadhikari.com.crowdfire.model.pojo.GarmentPojo;
import kausthubhadhikari.com.crowdfire.utils.misc.RxUtils;
import rx.Observable;

/**
 * Created by kausthubhadhikari on 21/12/16.
 */

public class Manager {
    private Database database;
    private RxUtils rxUtils;

    public Manager(Database database, RxUtils rxUtils) {
        this.database = database;
        this.rxUtils = rxUtils;
    }

    public Observable<ArrayList<GarmentPojo>> getUpperGarments() {
        Observable<ArrayList<GarmentPojo>> upperGarments = Observable.just(database.getUpperGarments()).compose(rxUtils.applySchedulers());
        return upperGarments;
    }

    public Observable<ArrayList<GarmentPojo>> getLowerGarments() {
        Observable<ArrayList<GarmentPojo>> lowerGarments = Observable.just(database.getLowerGarments()).compose(rxUtils.applySchedulers());
        return lowerGarments;
    }


}
