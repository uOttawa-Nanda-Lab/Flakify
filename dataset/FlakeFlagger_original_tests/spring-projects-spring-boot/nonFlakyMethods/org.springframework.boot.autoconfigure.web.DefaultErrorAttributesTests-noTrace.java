@Test public void noTrace() throws Exception {
  RuntimeException ex=new RuntimeException("Test");
  this.request.setAttribute("javax.servlet.error.exception",ex);
  Map<String,Object> attributes=this.errorAttributes.getErrorAttributes(this.requestAttributes,false);
  assertThat(attributes.get("trace"),nullValue());
}
