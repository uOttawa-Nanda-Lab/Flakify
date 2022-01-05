@Test public void testErrorWithEscape() throws Exception {
  MvcResult response=this.mockMvc.perform(get("/error").requestAttr("javax.servlet.error.exception",new RuntimeException("<script>alert('Hello World')</script>")).accept(MediaType.TEXT_HTML)).andExpect(status().isOk()).andReturn();
  String content=response.getResponse().getContentAsString();
  assertTrue("Wrong content: " + content,content.contains("&lt;script&gt;"));
  assertTrue("Wrong content: " + content,content.contains("Hello World"));
  assertTrue("Wrong content: " + content,content.contains("999"));
}
