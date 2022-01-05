@Test public void statusCode() throws Exception {
  this.chain=new MockFilterChain(){
    @Override public void doFilter(    ServletRequest request,    ServletResponse response) throws IOException, ServletException {
      assertThat(((HttpServletResponse)response).getStatus(),equalTo(200));
      super.doFilter(request,response);
    }
  }
;
  this.filter.doFilter(this.request,this.response,this.chain);
  assertThat(((HttpServletResponseWrapper)this.chain.getResponse()).getStatus(),equalTo(200));
}
