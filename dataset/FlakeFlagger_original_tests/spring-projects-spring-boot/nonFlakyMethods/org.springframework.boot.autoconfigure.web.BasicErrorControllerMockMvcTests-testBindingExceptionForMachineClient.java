@Test public void testBindingExceptionForMachineClient() throws Exception {
  MvcResult result=this.mockMvc.perform(get("/bind")).andExpect(status().is4xxClientError()).andReturn();
  MvcResult response=this.mockMvc.perform(new ErrorDispatcher(result,"/error")).andReturn();
  String content=response.getResponse().getContentAsString();
  assertTrue("Wrong content: " + content,content.contains("Error count: 1"));
}
