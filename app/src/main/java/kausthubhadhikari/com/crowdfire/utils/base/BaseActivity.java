package kausthubhadhikari.com.crowdfire.utils.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by kausthubhadhikari on 20/12/16.
 */

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getPresenter().unsubcribe();
    }

    // public abstract void inject(Injecttor component);

    public abstract P getPresenter();

}
