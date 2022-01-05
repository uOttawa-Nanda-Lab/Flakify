@Test public void testReloadCacheCall() throws Exception {
  when(mockRequest.getRequestURI()).thenReturn(ReloadCacheRequestHandler.ENDPOINT_URI);
  final ThreadLocal<Integer> status=new ThreadLocal<Integer>();
  final HttpServletResponse response=new HttpServletResponseWrapper(mockResponse){
    @Override public void setStatus(    final int sc){
      status.set(sc);
    }
  }
;
  Context.set(Context.webContext(mockRequest,response,mockFilterConfig));
  victim.init(mockFilterConfig);
  victim.doFilter(Context.get().getRequest(),Context.get().getResponse(),mockFilterChain);
  assertEquals(Integer.valueOf(HttpServletResponse.SC_OK),status.get());
}
