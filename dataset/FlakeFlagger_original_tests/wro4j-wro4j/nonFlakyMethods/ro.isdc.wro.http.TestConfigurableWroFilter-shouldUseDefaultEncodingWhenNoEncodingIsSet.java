@Test public void shouldUseDefaultEncodingWhenNoEncodingIsSet() throws Exception {
  final ConfigurableWroFilter filter=new SampleConfigurableWroFilter(){
    @Override protected void onRequestProcessed(){
      assertEquals(WroConfiguration.DEFAULT_ENCODING,Context.get().getConfig().getEncoding());
    }
  }
;
  filter.setEncoding(null);
  filter.init(mockFilterConfig);
  filter.doFilter(mockRequest,mockResponse,mockFilterChain);
}
