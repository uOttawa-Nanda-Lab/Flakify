@Test public void path() throws Exception {
  this.request.setAttribute("javax.servlet.error.request_uri","path");
  Map<String,Object> attributes=this.errorAttributes.getErrorAttributes(this.requestAttributes,false);
  assertThat(attributes.get("path"),equalTo((Object)"path"));
}
