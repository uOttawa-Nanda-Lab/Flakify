@Test public void testIsInteger() throws Exception {
  assertFalse(StringUtils.isInteger(null));
  assertFalse(StringUtils.isInteger(""));
  assertTrue(StringUtils.isInteger("123"));
}
