@Test public void testHome() throws Exception {
  this.mockMvc.perform(get("/")).andExpect(status().isOk()).andExpect(content().string(containsString("<title>Messages")));
}
