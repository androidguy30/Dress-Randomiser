package kausthubhadhikari.com.crowdfire.dagger.module;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.Theme;

import dagger.Module;
import dagger.Provides;
import kausthubhadhikari.com.crowdfire.R;
import kausthubhadhikari.com.crowdfire.dagger.scope.ActivityScope;
import kausthubhadhikari.com.crowdfire.model.data.Database;
import kausthubhadhikari.com.crowdfire.model.manager.Manager;
import kausthubhadhikari.com.crowdfire.utils.misc.AppUtils;
import kausthubhadhikari.com.crowdfire.utils.misc.RxUtils;
import kausthubhadhikari.com.crowdfire.view.MainActivity;

/**
 * Created by kausthubhadhikari on 21/12/16.
 */
@Module
public class MainModule {

    private MainActivity activity;

    public MainModule(MainActivity activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityScope
    public MaterialDialog providesMaterialDialog() {
        MaterialDialog materialDialog = new MaterialDialog.Builder(activity)
                .customView(R.layout.progress_dialog, false)
                .cancelable(false)
                .theme(Theme.LIGHT)
                .build();
        materialDialog.getWindow().setLayout(AppUtils.dpTox(85), AppUtils.dpTox(85));
        materialDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#90000000")));

        return materialDialog;
    }

    @Provides
    @ActivityScope
    public Database providesDatabase() {
        return new Database(activity);
    }

    @ActivityScope
    @Provides
    public Manager providesManager(RxUtils rxUtils,Database database){
        return new Manager(database,rxUtils);
    }

}
