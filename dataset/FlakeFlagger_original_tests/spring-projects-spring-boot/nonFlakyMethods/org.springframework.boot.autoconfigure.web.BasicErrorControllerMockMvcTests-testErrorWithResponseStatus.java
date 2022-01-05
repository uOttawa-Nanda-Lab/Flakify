@Test public void testErrorWithResponseStatus() throws Exception {
  MvcResult result=this.mockMvc.perform(get("/bang")).andExpect(status().isNotFound()).andReturn();
  MvcResult response=this.mockMvc.perform(new ErrorDispatcher(result,"/error")).andReturn();
  String content=response.getResponse().getContentAsString();
  assertTrue("Wrong content: " + content,content.contains("Expected!"));
}
