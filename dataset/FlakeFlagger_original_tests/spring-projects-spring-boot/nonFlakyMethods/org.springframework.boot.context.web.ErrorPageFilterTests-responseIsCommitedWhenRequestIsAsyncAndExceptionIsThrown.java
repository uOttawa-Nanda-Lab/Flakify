@Test public void responseIsCommitedWhenRequestIsAsyncAndExceptionIsThrown() throws Exception {
  this.filter.addErrorPages(new ErrorPage("/error"));
  this.request.setAsyncStarted(true);
  this.chain=new MockFilterChain(){
    @Override public void doFilter(    ServletRequest request,    ServletResponse response) throws IOException, ServletException {
      super.doFilter(request,response);
      throw new RuntimeException("BAD");
    }
  }
;
  this.filter.doFilter(this.request,this.response,this.chain);
  assertThat(this.chain.getRequest(),equalTo((ServletRequest)this.request));
  assertThat(((HttpServletResponseWrapper)this.chain.getResponse()).getResponse(),equalTo((ServletResponse)this.response));
  assertTrue(this.response.isCommitted());
}
