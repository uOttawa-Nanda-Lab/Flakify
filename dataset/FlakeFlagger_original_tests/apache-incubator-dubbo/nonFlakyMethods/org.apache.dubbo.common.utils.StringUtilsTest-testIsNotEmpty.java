@Test public void testIsNotEmpty() throws Exception {
  assertFalse(StringUtils.isNotEmpty(null));
  assertFalse(StringUtils.isNotEmpty(""));
  assertTrue(StringUtils.isNotEmpty("abc"));
}
