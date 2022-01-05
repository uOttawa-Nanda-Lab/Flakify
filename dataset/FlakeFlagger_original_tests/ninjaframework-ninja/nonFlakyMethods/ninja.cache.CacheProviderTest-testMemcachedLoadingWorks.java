@Test public void testMemcachedLoadingWorks(){
  NinjaPropertiesImpl ninjaProperties=new NinjaPropertiesImpl(NinjaMode.test);
  ninjaProperties.setProperty(NinjaConstant.CACHE_IMPLEMENTATION,CacheMemcachedImpl.class.getName());
  ninjaProperties.setProperty(NinjaConstant.MEMCACHED_HOST,"127.0.0.1:1234");
  Injector injector=Guice.createInjector(new Configuration(ninjaProperties),LifecycleSupport.getModule());
  Logger logger=injector.getInstance(Logger.class);
  CacheProvider cacheProvider=new CacheProvider(injector,ninjaProperties,logger);
  assertTrue(cacheProvider.get() instanceof CacheMemcachedImpl);
}
