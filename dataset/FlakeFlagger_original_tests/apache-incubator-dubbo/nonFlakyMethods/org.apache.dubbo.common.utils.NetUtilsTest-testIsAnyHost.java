@Test public void testIsAnyHost() throws Exception {
  assertTrue(NetUtils.isAnyHost("0.0.0.0"));
  assertFalse(NetUtils.isAnyHost("1.1.1.1"));
}
