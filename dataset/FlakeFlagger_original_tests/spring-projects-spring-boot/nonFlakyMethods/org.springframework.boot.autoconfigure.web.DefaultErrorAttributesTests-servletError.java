@Test public void servletError() throws Exception {
  RuntimeException ex=new RuntimeException("Test");
  this.request.setAttribute("javax.servlet.error.exception",ex);
  Map<String,Object> attributes=this.errorAttributes.getErrorAttributes(this.requestAttributes,false);
  assertThat(this.errorAttributes.getError(this.requestAttributes),sameInstance((Object)ex));
  assertThat(attributes.get("exception"),equalTo((Object)RuntimeException.class.getName()));
  assertThat(attributes.get("message"),equalTo((Object)"Test"));
}
