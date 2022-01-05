@Test public void testIsEmpty() throws Exception {
  assertTrue(StringUtils.isEmpty(null));
  assertTrue(StringUtils.isEmpty(""));
  assertFalse(StringUtils.isEmpty("abc"));
}
