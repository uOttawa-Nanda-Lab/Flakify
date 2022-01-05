@Test public void testToURL() throws Exception {
  String url=NetUtils.toURL("dubbo","host",1234,"foo");
  assertThat(url,equalTo("dubbo://host:1234/foo"));
}
