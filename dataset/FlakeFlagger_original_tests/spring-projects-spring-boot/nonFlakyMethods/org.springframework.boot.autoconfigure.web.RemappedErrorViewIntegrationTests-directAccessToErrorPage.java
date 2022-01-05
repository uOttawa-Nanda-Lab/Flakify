@Test public void directAccessToErrorPage() throws Exception {
  String content=this.template.getForObject("http://localhost:" + this.port + "/spring/error",String.class);
  assertTrue("Wrong content: " + content,content.contains("error"));
  assertTrue("Wrong content: " + content,content.contains("999"));
}
