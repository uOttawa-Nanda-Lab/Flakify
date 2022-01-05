@Test public void testConfigureCacheUpdatePeriodWithPropertiesFileSet(){
  factory=new PropertiesAndFilterConfigWroConfigurationFactory(filterConfig){
    @Override protected Properties newDefaultProperties(){
      final Properties props=new Properties();
      props.put(ConfigConstants.cacheUpdatePeriod.name(),"15");
      props.put(ConfigConstants.modelUpdatePeriod.name(),"30");
      return props;
    }
  }
;
  Mockito.when(filterConfig.getInitParameter(ConfigConstants.cacheUpdatePeriod.name())).thenReturn("10");
  final WroConfiguration config=factory.create();
  Assert.assertEquals(10,config.getCacheUpdatePeriod());
  Assert.assertEquals(true,config.isDebug());
  Assert.assertEquals(10,config.getCacheUpdatePeriod());
  Assert.assertEquals(30,config.getModelUpdatePeriod());
}
