@Test public void testCreate() throws Exception {
  this.mockMvc.perform(post("/").param("text","FOO text").param("summary","FOO")).andExpect(status().isMovedTemporarily()).andExpect(header().string("location",RegexMatcher.matches("/[0-9]+")));
}
