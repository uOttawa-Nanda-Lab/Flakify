@Test public void oncePerRequest() throws Exception {
  this.chain=new MockFilterChain(){
    @Override public void doFilter(    ServletRequest request,    ServletResponse response) throws IOException, ServletException {
      ((HttpServletResponse)response).sendError(400,"BAD");
      assertNotNull(request.getAttribute("FILTER.FILTERED"));
      super.doFilter(request,response);
    }
  }
;
  this.filter.init(new MockFilterConfig("FILTER"));
  this.filter.doFilter(this.request,this.response,this.chain);
}
