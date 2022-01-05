@Test public void shouldUseConfiguredEncodingWhenSet() throws Exception {
  final String encoding="UTF-16";
  final ConfigurableWroFilter filter=new SampleConfigurableWroFilter(){
    @Override protected void onRequestProcessed(){
      assertEquals(encoding,Context.get().getConfig().getEncoding());
    }
  }
;
  filter.setEncoding(encoding);
  filter.init(mockFilterConfig);
  filter.doFilter(mockRequest,mockResponse,mockFilterChain);
}
