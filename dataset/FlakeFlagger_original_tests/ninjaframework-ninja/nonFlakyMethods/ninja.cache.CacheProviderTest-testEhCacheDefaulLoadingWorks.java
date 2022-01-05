@Test public void testEhCacheDefaulLoadingWorks(){
  NinjaPropertiesImpl ninjaProperties=new NinjaPropertiesImpl(NinjaMode.test);
  ninjaProperties.setProperty(NinjaConstant.CACHE_IMPLEMENTATION,null);
  Injector injector=Guice.createInjector(new Configuration(ninjaProperties),LifecycleSupport.getModule());
  Logger logger=injector.getInstance(Logger.class);
  CacheProvider cacheProvider=new CacheProvider(injector,ninjaProperties,logger);
  assertTrue(cacheProvider.get() instanceof CacheEhCacheImpl);
}
