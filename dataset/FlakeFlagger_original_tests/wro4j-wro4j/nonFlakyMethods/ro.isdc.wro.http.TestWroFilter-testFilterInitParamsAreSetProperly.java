/** 
 * Set filter init params with proper values and check they are the same in  {@link WroConfiguration} object.
 */
@Test public void testFilterInitParamsAreSetProperly() throws Exception {
  setConfigurationMode(FilterConfigWroConfigurationFactory.PARAM_VALUE_DEPLOYMENT);
  when(mockFilterConfig.getInitParameter(ConfigConstants.gzipResources.name())).thenReturn("false");
  when(mockFilterConfig.getInitParameter(ConfigConstants.cacheUpdatePeriod.name())).thenReturn("10");
  when(mockFilterConfig.getInitParameter(ConfigConstants.modelUpdatePeriod.name())).thenReturn("100");
  victim.init(mockFilterConfig);
  final WroConfiguration config=victim.getConfiguration();
  Assert.assertEquals(false,config.isDebug());
  Assert.assertEquals(false,config.isGzipEnabled());
  Assert.assertEquals(10,config.getCacheUpdatePeriod());
  Assert.assertEquals(100,config.getModelUpdatePeriod());
}
