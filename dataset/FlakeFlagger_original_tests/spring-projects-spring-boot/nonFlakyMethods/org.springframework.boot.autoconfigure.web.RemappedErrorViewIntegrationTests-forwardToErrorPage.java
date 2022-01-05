@Test public void forwardToErrorPage() throws Exception {
  String content=this.template.getForObject("http://localhost:" + this.port + "/spring/",String.class);
  assertTrue("Wrong content: " + content,content.contains("error"));
  assertTrue("Wrong content: " + content,content.contains("500"));
}
