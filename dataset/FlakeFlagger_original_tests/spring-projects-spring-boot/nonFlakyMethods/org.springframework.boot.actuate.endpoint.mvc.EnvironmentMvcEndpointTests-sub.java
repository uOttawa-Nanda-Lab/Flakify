@Test public void sub() throws Exception {
  this.mvc.perform(get("/env/foo")).andExpect(status().isOk()).andExpect(content().string(equalToIgnoringCase("bar")));
}
