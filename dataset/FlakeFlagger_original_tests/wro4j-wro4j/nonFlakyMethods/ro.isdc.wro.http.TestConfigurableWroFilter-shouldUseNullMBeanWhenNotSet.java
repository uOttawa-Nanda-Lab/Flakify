@Test public void shouldUseNullMBeanWhenNotSet() throws Exception {
  final ConfigurableWroFilter filter=new SampleConfigurableWroFilter(){
    @Override protected void onRequestProcessed(){
      assertNull(Context.get().getConfig().getMbeanName());
    }
  }
;
  filter.setMbeanName(null);
  filter.init(mockFilterConfig);
  filter.doFilter(mockRequest,mockResponse,mockFilterChain);
}
