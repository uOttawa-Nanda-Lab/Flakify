@Test public void testHome() throws Exception {
  this.mvc.perform(get("/")).andExpect(status().isOk()).andExpect(content().string("Bath"));
}
