@Test public void testGetHostName() throws Exception {
  assertNotNull(NetUtils.getHostName("127.0.0.1"));
}
