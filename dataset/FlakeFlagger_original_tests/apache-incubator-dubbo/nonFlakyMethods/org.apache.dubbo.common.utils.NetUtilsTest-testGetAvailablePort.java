@Test public void testGetAvailablePort() throws Exception {
  assertThat(NetUtils.getAvailablePort(),greaterThan(0));
  assertThat(NetUtils.getAvailablePort(12345),greaterThanOrEqualTo(12345));
  assertThat(NetUtils.getAvailablePort(-1),greaterThanOrEqualTo(30000));
}
