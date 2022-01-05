@Test public void responseIsNotCommitedWhenRequestIsAsync() throws Exception {
  this.request.setAsyncStarted(true);
  this.filter.doFilter(this.request,this.response,this.chain);
  assertThat(this.chain.getRequest(),equalTo((ServletRequest)this.request));
  assertThat(((HttpServletResponseWrapper)this.chain.getResponse()).getResponse(),equalTo((ServletResponse)this.response));
  assertFalse(this.response.isCommitted());
}
