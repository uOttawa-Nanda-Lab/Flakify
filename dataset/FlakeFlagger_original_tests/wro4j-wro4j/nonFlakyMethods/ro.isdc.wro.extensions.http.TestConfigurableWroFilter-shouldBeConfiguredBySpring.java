@Test public void shouldBeConfiguredBySpring() throws Exception {
  filter.init(mockFilterConfig);
  final WroConfiguration config=filter.getConfiguration();
  Assert.assertEquals(10,config.getCacheUpdatePeriod());
  Assert.assertEquals(20,config.getModelUpdatePeriod());
  Assert.assertEquals(false,config.isGzipEnabled());
  Assert.assertEquals(true,config.isDebug());
  Assert.assertEquals(false,config.isIgnoreMissingResources());
  Assert.assertEquals(true,config.isDisableCache());
  Assert.assertEquals(false,config.isJmxEnabled());
}
