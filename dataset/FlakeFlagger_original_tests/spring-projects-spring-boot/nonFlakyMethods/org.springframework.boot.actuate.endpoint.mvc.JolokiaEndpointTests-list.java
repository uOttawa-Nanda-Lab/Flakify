@Test public void list() throws Exception {
  this.mvc.perform(get("/jolokia/list/java.lang/type=Memory/attr")).andExpect(status().isOk()).andExpect(content().string(containsString("NonHeapMemoryUsage")));
}
