@Test public void testToAddress() throws Exception {
  InetSocketAddress address=NetUtils.toAddress("localhost:1234");
  assertThat(address.getHostName(),equalTo("localhost"));
  assertThat(address.getPort(),equalTo(1234));
  address=NetUtils.toAddress("localhost");
  assertThat(address.getHostName(),equalTo("localhost"));
  assertThat(address.getPort(),equalTo(0));
}
