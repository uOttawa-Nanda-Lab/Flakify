@Test public void testFilterLocalHost() throws Exception {
  assertNull(NetUtils.filterLocalHost(null));
  assertEquals(NetUtils.filterLocalHost(""),"");
  String host=NetUtils.filterLocalHost("dubbo://127.0.0.1:8080/foo");
  assertThat(host,equalTo("dubbo://" + NetUtils.getLocalHost() + ":8080/foo"));
  host=NetUtils.filterLocalHost("127.0.0.1:8080");
  assertThat(host,equalTo(NetUtils.getLocalHost() + ":8080"));
  host=NetUtils.filterLocalHost("0.0.0.0");
  assertThat(host,equalTo(NetUtils.getLocalHost()));
  host=NetUtils.filterLocalHost("88.88.88.88");
  assertThat(host,equalTo(host));
}
