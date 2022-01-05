@Test public void testIsValidLocalHost() throws Exception {
  assertTrue(NetUtils.isValidLocalHost("1.2.3.4"));
}
