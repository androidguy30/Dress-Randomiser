package kausthubhadhikari.com.crowdfire.dagger.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import kausthubhadhikari.com.crowdfire.AppController;
import kausthubhadhikari.com.crowdfire.utils.misc.RxUtils;

/**
 * Created by kausthubhadhikari on 20/12/16.
 */
@Module
public class AppModule {

    private final AppController appController;

    public AppModule(AppController appController) {
        this.appController = appController;
    }

    @Singleton
    @Provides
    public RxUtils providesRxUtils() {
        return new RxUtils();
    }

}
