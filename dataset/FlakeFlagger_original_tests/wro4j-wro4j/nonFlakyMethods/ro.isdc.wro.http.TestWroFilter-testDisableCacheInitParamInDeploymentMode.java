@Test public void testDisableCacheInitParamInDeploymentMode() throws Exception {
  when(mockFilterConfig.getInitParameter(FilterConfigWroConfigurationFactory.PARAM_CONFIGURATION)).thenReturn(FilterConfigWroConfigurationFactory.PARAM_VALUE_DEPLOYMENT);
  when(mockFilterConfig.getInitParameter(ConfigConstants.disableCache.name())).thenReturn("true");
  victim.init(mockFilterConfig);
  Assert.assertEquals(false,victim.getConfiguration().isDebug());
  Assert.assertEquals(false,victim.getConfiguration().isDisableCache());
}
