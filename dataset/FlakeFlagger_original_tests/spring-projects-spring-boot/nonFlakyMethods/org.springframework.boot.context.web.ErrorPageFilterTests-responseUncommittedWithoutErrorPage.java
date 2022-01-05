@Test public void responseUncommittedWithoutErrorPage() throws Exception {
  this.chain=new MockFilterChain(){
    @Override public void doFilter(    ServletRequest request,    ServletResponse response) throws IOException, ServletException {
      ((HttpServletResponse)response).sendError(400,"BAD");
      super.doFilter(request,response);
    }
  }
;
  this.filter.doFilter(this.request,this.response,this.chain);
  assertThat(this.chain.getRequest(),equalTo((ServletRequest)this.request));
  assertThat(((HttpServletResponseWrapper)this.chain.getResponse()).getResponse(),equalTo((ServletResponse)this.response));
  assertThat(((HttpServletResponseWrapper)this.chain.getResponse()).getStatus(),equalTo(400));
  assertThat(this.response.getForwardedUrl(),is(nullValue()));
  assertTrue(this.response.isCommitted());
}
