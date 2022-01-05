@Test public void testFilterWithPropertiesSet() throws Exception {
  final ConfigurableWroFilter filter=new ConfigurableWroFilter(){
    @Override protected void onRequestProcessed(){
      assertEquals(10,Context.get().getConfig().getCacheUpdatePeriod());
    }
  }
;
  final Properties properties=new Properties();
  properties.setProperty(ConfigConstants.cacheUpdatePeriod.name(),"10");
  filter.setProperties(properties);
  filter.init(mockFilterConfig);
  filter.doFilter(mockRequest,mockResponse,mockFilterChain);
}
