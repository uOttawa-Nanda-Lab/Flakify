@Test public void shouldUseConfiguredMBeanNameWhenSet() throws Exception {
  final String mbeanName="mbean";
  final ConfigurableWroFilter filter=new SampleConfigurableWroFilter(){
    @Override protected void onRequestProcessed(){
      assertEquals(mbeanName,Context.get().getConfig().getMbeanName());
    }
  }
;
  filter.setMbeanName(mbeanName);
  filter.init(mockFilterConfig);
  filter.doFilter(mockRequest,mockResponse,mockFilterChain);
}
