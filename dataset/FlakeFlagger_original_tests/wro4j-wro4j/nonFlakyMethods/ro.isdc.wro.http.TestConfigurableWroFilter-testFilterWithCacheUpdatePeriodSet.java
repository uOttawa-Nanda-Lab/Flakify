@Test public void testFilterWithCacheUpdatePeriodSet() throws Exception {
  final ConfigurableWroFilter filter=new SampleConfigurableWroFilter(){
    @Override protected void onRequestProcessed(){
      assertEquals(20,Context.get().getConfig().getCacheUpdatePeriod());
    }
  }
;
  filter.setCacheUpdatePeriod(20);
  filter.init(mockFilterConfig);
  filter.doFilter(mockRequest,mockResponse,mockFilterChain);
}
