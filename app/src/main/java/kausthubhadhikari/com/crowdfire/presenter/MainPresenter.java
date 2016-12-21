package kausthubhadhikari.com.crowdfire.presenter;

import java.util.ArrayList;

import kausthubhadhikari.com.crowdfire.model.data.Database;
import kausthubhadhikari.com.crowdfire.model.manager.Manager;
import kausthubhadhikari.com.crowdfire.model.pojo.GarmentPojo;
import kausthubhadhikari.com.crowdfire.utils.base.BasePresenter;
import kausthubhadhikari.com.crowdfire.utils.misc.AppUtils;
import kausthubhadhikari.com.crowdfire.utils.misc.RxUtils;
import kausthubhadhikari.com.crowdfire.view.MainView;
import rx.Subscription;

/**
 * Created by kausthubhadhikari on 20/12/16.
 */

public class MainPresenter extends BasePresenter {

    private MainView mainView;
    private Database database;
    private RxUtils rxUtils;
    private Manager manager;
    private AppUtils appUtils;

    public MainPresenter(MainView mainView, Database database, RxUtils rxUtils, Manager manager, AppUtils appUtils) {
        super(mainView);
        this.mainView = mainView;
        this.database = database;
        this.rxUtils = rxUtils;
        this.manager = manager;
        this.appUtils = appUtils;
    }

    @Override
    public void onViewCreated(boolean isLaunched) {
        mainView.setupView();
        if (isLaunched) {

        }
    }

    public void addData(GarmentPojo data) {
        database.addGarment(data);
    }

    public void retrieveUpperGarment() {
        Subscription subscription = manager.getUpperGarments().subscribe(this::fetchedData, this::onThrowsError);
        addSubsciption(subscription);
    }

    public void retrieveLowerGarment() {
        Subscription subscription = manager.getLowerGarments().subscribe(this::fetchedData, this::onThrowsError);
        addSubsciption(subscription);
    }

    public void fetchedData(ArrayList<GarmentPojo> data) {

    }

    public void onThrowsError(Throwable throwable) {

    }

}
