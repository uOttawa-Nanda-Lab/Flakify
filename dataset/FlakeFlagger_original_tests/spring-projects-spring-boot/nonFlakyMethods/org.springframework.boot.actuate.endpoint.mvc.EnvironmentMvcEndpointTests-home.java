@Test public void home() throws Exception {
  this.mvc.perform(get("/env")).andExpect(status().isOk()).andExpect(content().string(containsString("systemProperties")));
}
