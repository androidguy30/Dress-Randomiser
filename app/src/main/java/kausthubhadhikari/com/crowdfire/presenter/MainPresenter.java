package kausthubhadhikari.com.crowdfire.presenter;

import kausthubhadhikari.com.crowdfire.model.data.Database;
import kausthubhadhikari.com.crowdfire.model.pojo.GarmentPojo;
import kausthubhadhikari.com.crowdfire.utils.base.BasePresenter;
import kausthubhadhikari.com.crowdfire.view.MainView;

/**
 * Created by kausthubhadhikari on 20/12/16.
 */

public class MainPresenter extends BasePresenter {

    private MainView mainView;
    private Database database;

    public MainPresenter(MainView mainView, Database database) {
        super(mainView);
        this.mainView = mainView;
        this.database = database;
    }

    @Override
    public void onViewCreated(boolean isLaunched) {

    }

    public void addData(GarmentPojo data) {
        database.addGarment(data);
    }



}
