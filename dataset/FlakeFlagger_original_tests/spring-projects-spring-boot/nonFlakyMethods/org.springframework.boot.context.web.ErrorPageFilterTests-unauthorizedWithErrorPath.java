@Test public void unauthorizedWithErrorPath() throws Exception {
  this.filter.addErrorPages(new ErrorPage("/error"));
  this.chain=new MockFilterChain(){
    @Override public void doFilter(    ServletRequest request,    ServletResponse response) throws IOException, ServletException {
      ((HttpServletResponse)response).sendError(401,"UNAUTHORIZED");
      super.doFilter(request,response);
    }
  }
;
  this.filter.doFilter(this.request,this.response,this.chain);
  assertThat(this.chain.getRequest(),equalTo((ServletRequest)this.request));
  HttpServletResponseWrapper wrapper=(HttpServletResponseWrapper)this.chain.getResponse();
  assertThat(wrapper.getResponse(),equalTo((ServletResponse)this.response));
  assertTrue(this.response.isCommitted());
  assertThat(wrapper.getStatus(),equalTo(401));
  assertThat(this.response.getStatus(),equalTo(401));
  assertThat(this.response.getForwardedUrl(),equalTo("/error"));
}
