@Test public void nullMessage() throws Exception {
  this.request.setAttribute("javax.servlet.error.exception",new RuntimeException());
  this.request.setAttribute("javax.servlet.error.message","Test");
  Map<String,Object> attributes=this.errorAttributes.getErrorAttributes(this.requestAttributes,false);
  assertThat(attributes.get("exception"),equalTo((Object)RuntimeException.class.getName()));
  assertThat(attributes.get("message"),equalTo((Object)"Test"));
}
