package daniyaramangeldy.yandextranslate.di.modules;

import com.activeandroid.query.Select;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by daniyaramangeldy on 21.04.17.
 */

@Module(includes = {AppModule.class})
public class DataBaseModule {
    @Provides
    @Singleton
    public Select getSelect(){
        return new Select();
    }
}
