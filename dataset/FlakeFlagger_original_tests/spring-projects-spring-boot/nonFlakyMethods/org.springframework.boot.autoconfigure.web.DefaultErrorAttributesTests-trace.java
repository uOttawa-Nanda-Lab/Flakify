@Test public void trace() throws Exception {
  RuntimeException ex=new RuntimeException("Test");
  this.request.setAttribute("javax.servlet.error.exception",ex);
  Map<String,Object> attributes=this.errorAttributes.getErrorAttributes(this.requestAttributes,true);
  assertThat(attributes.get("trace").toString(),startsWith("java.lang"));
}
