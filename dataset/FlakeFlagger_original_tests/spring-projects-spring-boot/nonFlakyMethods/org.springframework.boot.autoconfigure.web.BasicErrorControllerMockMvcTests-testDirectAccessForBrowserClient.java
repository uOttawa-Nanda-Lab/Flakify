@Test public void testDirectAccessForBrowserClient() throws Exception {
  MvcResult response=this.mockMvc.perform(get("/error").accept(MediaType.TEXT_HTML)).andExpect(status().isOk()).andReturn();
  String content=response.getResponse().getContentAsString();
  assertTrue("Wrong content: " + content,content.contains("ERROR_BEAN"));
}
