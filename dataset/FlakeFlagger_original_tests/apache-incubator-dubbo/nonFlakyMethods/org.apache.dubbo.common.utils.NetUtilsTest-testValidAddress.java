@Test public void testValidAddress() throws Exception {
  assertTrue(NetUtils.isValidAddress("10.20.130.230:20880"));
  assertFalse(NetUtils.isValidAddress("10.20.130.230"));
  assertFalse(NetUtils.isValidAddress("10.20.130.230:666666"));
}
