@Test public void testErrorForBrowserClient() throws Exception {
  MvcResult response=this.mockMvc.perform(get("/error").accept(MediaType.TEXT_HTML)).andExpect(status().isOk()).andReturn();
  String content=response.getResponse().getContentAsString();
  assertTrue("Wrong content: " + content,content.contains("<html>"));
  assertTrue("Wrong content: " + content,content.contains("999"));
}
