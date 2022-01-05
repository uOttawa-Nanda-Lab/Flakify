@Test public void testGetIpByHost() throws Exception {
  assertThat(NetUtils.getIpByHost("localhost"),equalTo("127.0.0.1"));
  assertThat(NetUtils.getIpByHost("dubbo"),equalTo("dubbo"));
}
