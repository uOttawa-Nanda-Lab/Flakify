@Test public void read() throws Exception {
  this.mvc.perform(get("/admin/jolokia/read/java.lang:type=Memory")).andExpect(status().isOk()).andExpect(content().string(containsString("NonHeapMemoryUsage")));
}
