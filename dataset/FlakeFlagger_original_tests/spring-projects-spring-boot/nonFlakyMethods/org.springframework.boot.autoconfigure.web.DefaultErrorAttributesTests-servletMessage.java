@Test public void servletMessage() throws Exception {
  this.request.setAttribute("javax.servlet.error.message","Test");
  Map<String,Object> attributes=this.errorAttributes.getErrorAttributes(this.requestAttributes,false);
  assertThat(attributes.get("exception"),nullValue());
  assertThat(attributes.get("message"),equalTo((Object)"Test"));
}
