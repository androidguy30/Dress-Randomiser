package kausthubhadhikari.com.crowdfire.dagger.component;

import com.afollestad.materialdialogs.MaterialDialog;

import dagger.Component;
import kausthubhadhikari.com.crowdfire.dagger.module.MainModule;
import kausthubhadhikari.com.crowdfire.dagger.scope.ActivityScope;
import kausthubhadhikari.com.crowdfire.model.data.Database;
import kausthubhadhikari.com.crowdfire.model.manager.Manager;
import kausthubhadhikari.com.crowdfire.view.MainActivity;

/**
 * Created by kausthubhadhikari on 21/12/16.
 */
@ActivityScope
@Component(modules = {MainModule.class}, dependencies = {AppComponent.class})
public interface MainComponent {
    void inject(MainActivity activity);

    Database providesDatabase();

    Manager providesManager();

    MaterialDialog providesMaterialDialog();
}
