@Test public void unwrapServletException() throws Exception {
  RuntimeException ex=new RuntimeException("Test");
  ServletException wrapped=new ServletException(new ServletException(ex));
  this.request.setAttribute("javax.servlet.error.exception",wrapped);
  Map<String,Object> attributes=this.errorAttributes.getErrorAttributes(this.requestAttributes,false);
  assertThat(this.errorAttributes.getError(this.requestAttributes),sameInstance((Object)wrapped));
  assertThat(attributes.get("exception"),equalTo((Object)RuntimeException.class.getName()));
  assertThat(attributes.get("message"),equalTo((Object)"Test"));
}
