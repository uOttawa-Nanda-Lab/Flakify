@Test public void testThatSettingWrongCacheImplementationYieldsException(){
  NinjaPropertiesImpl ninjaProperties=new NinjaPropertiesImpl(NinjaMode.test);
  ninjaProperties.setProperty(NinjaConstant.CACHE_IMPLEMENTATION,"not_existing_implementation");
  Injector injector=Guice.createInjector(new Configuration(ninjaProperties),LifecycleSupport.getModule());
  Logger logger=injector.getInstance(Logger.class);
  thrown.expect(RuntimeException.class);
  CacheProvider cacheProvider=new CacheProvider(injector,ninjaProperties,logger);
  cacheProvider.get();
}
