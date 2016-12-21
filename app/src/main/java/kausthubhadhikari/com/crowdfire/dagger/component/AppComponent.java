package kausthubhadhikari.com.crowdfire.dagger.component;

import javax.inject.Singleton;

import dagger.Component;
import kausthubhadhikari.com.crowdfire.dagger.module.AppModule;

/**
 * Created by kausthubhadhikari on 20/12/16.
 */
@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

}
