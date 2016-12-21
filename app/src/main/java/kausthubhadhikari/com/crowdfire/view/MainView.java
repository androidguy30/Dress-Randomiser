package kausthubhadhikari.com.crowdfire.view;

import kausthubhadhikari.com.crowdfire.utils.base.BaseView;

/**
 * Created by kausthubhadhikari on 20/12/16.
 */

public interface MainView extends BaseView {

    public void setupView();

    public void showProgress();

    public void hideProgress();

    public void showSnackbar(String message);

}
