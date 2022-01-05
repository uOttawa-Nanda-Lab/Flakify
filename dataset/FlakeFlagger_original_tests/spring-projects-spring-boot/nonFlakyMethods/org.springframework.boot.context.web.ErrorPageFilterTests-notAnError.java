@Test public void notAnError() throws Exception {
  this.filter.doFilter(this.request,this.response,this.chain);
  assertThat(this.chain.getRequest(),equalTo((ServletRequest)this.request));
  assertThat(((HttpServletResponseWrapper)this.chain.getResponse()).getResponse(),equalTo((ServletResponse)this.response));
  assertTrue(this.response.isCommitted());
  assertThat(this.response.getForwardedUrl(),is(nullValue()));
}
