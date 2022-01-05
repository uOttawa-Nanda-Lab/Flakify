@Test public void search() throws Exception {
  this.mvc.perform(get("/jolokia/search/java.lang:*")).andExpect(status().isOk()).andExpect(content().string(containsString("GarbageCollector")));
}
