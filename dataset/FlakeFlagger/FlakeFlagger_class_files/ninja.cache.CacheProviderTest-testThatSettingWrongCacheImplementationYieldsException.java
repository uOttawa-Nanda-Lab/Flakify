package ninja.cache;

import static org.junit.Assert.assertTrue;
import ninja.Configuration;
import ninja.lifecycle.LifecycleSupport;
import ninja.utils.NinjaConstant;
import ninja.utils.NinjaMode;
import ninja.utils.NinjaPropertiesImpl;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.slf4j.Logger;

import com.google.inject.Guice;
import com.google.inject.Injector;


public class CacheProviderTest {
    
    @Rule
    public ExpectedException thrown = ExpectedException.none();
    

    @Test public void testThatSettingWrongCacheImplementationYieldsException(){NinjaPropertiesImpl ninjaProperties=new NinjaPropertiesImpl(NinjaMode.test);ninjaProperties.setProperty(NinjaConstant.CACHE_IMPLEMENTATION,"not_existing_implementation");Injector injector=Guice.createInjector(new Configuration(ninjaProperties),LifecycleSupport.getModule());Logger logger=injector.getInstance(Logger.class);thrown.expect(RuntimeException.class);CacheProvider cacheProvider=new CacheProvider(injector,ninjaProperties,logger);cacheProvider.get();}

}
