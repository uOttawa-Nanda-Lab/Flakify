@Test public void testDisableCacheInitParamInDevelopmentMode() throws Exception {
  when(mockFilterConfig.getInitParameter(ConfigConstants.disableCache.name())).thenReturn("true");
  victim.init(mockFilterConfig);
  Assert.assertEquals(true,victim.getConfiguration().isDebug());
  Assert.assertEquals(true,victim.getConfiguration().isDisableCache());
}
