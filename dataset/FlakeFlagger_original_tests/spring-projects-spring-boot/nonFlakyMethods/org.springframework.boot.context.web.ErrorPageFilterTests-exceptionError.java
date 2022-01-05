@Test public void exceptionError() throws Exception {
  this.filter.addErrorPages(new ErrorPage(RuntimeException.class,"/500"));
  this.chain=new MockFilterChain(){
    @Override public void doFilter(    ServletRequest request,    ServletResponse response) throws IOException, ServletException {
      super.doFilter(request,response);
      throw new RuntimeException("BAD");
    }
  }
;
  this.filter.doFilter(this.request,this.response,this.chain);
  assertThat(((HttpServletResponseWrapper)this.chain.getResponse()).getStatus(),equalTo(500));
  assertThat(this.request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE),equalTo((Object)500));
  assertThat(this.request.getAttribute(RequestDispatcher.ERROR_MESSAGE),equalTo((Object)"BAD"));
  assertThat(this.request.getAttribute(RequestDispatcher.ERROR_EXCEPTION_TYPE),equalTo((Object)RuntimeException.class.getName()));
  assertTrue(this.response.isCommitted());
  assertThat(this.response.getForwardedUrl(),equalTo("/500"));
}
