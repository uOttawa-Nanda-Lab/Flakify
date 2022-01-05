@Test public void testIsBlank() throws Exception {
  assertTrue(StringUtils.isBlank(null));
  assertTrue(StringUtils.isBlank(""));
  assertFalse(StringUtils.isBlank("abc"));
}
