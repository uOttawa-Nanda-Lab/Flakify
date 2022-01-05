@Test public void testDirectAccessForMachineClient() throws Exception {
  MvcResult response=this.mockMvc.perform(get("/error")).andExpect(status().is5xxServerError()).andReturn();
  String content=response.getResponse().getContentAsString();
  assertTrue("Wrong content: " + content,content.contains("999"));
}
