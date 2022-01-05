@Test public void statusErrorWithCommittedResponse() throws Exception {
  this.filter.addErrorPages(new ErrorPage(HttpStatus.BAD_REQUEST,"/400"));
  this.chain=new MockFilterChain(){
    @Override public void doFilter(    ServletRequest request,    ServletResponse response) throws IOException, ServletException {
      ((HttpServletResponse)response).sendError(400,"BAD");
      response.flushBuffer();
      super.doFilter(request,response);
    }
  }
;
  this.filter.doFilter(this.request,this.response,this.chain);
  assertThat(((HttpServletResponseWrapper)this.chain.getResponse()).getStatus(),equalTo(400));
  assertTrue(this.response.isCommitted());
  assertThat(this.response.getForwardedUrl(),is(nullValue()));
}
