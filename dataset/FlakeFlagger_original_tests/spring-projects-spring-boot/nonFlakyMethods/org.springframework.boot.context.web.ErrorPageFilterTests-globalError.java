@Test public void globalError() throws Exception {
  this.filter.addErrorPages(new ErrorPage("/error"));
  this.chain=new MockFilterChain(){
    @Override public void doFilter(    ServletRequest request,    ServletResponse response) throws IOException, ServletException {
      ((HttpServletResponse)response).sendError(400,"BAD");
      super.doFilter(request,response);
    }
  }
;
  this.filter.doFilter(this.request,this.response,this.chain);
  assertThat(((HttpServletResponseWrapper)this.chain.getResponse()).getStatus(),equalTo(400));
  assertThat(this.request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE),equalTo((Object)400));
  assertThat(this.request.getAttribute(RequestDispatcher.ERROR_MESSAGE),equalTo((Object)"BAD"));
  assertTrue(this.response.isCommitted());
  assertThat(this.response.getForwardedUrl(),equalTo("/error"));
}
