package kausthubhadhikari.com.crowdfire.view;

import java.util.ArrayList;

import kausthubhadhikari.com.crowdfire.model.pojo.GarmentPojo;
import kausthubhadhikari.com.crowdfire.utils.base.BaseView;

/**
 * Created by kausthubhadhikari on 20/12/16.
 */

public interface MainView extends BaseView {

    public void setupView();

    public void showProgress();

    public void hideProgress();

    public void showSnackbar(String message);

    public void setUpperGarmentData(ArrayList<GarmentPojo> data);

    public void setLowerGarmentData(ArrayList<GarmentPojo> data);

    public void addUpperGarment(GarmentPojo data);

    public void addLowerGarment(GarmentPojo data);

}
