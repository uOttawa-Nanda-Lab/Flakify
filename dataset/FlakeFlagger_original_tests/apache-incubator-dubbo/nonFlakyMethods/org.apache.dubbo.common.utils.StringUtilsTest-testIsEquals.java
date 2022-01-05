@Test public void testIsEquals() throws Exception {
  assertTrue(StringUtils.isEquals(null,null));
  assertFalse(StringUtils.isEquals(null,""));
  assertTrue(StringUtils.isEquals("abc","abc"));
  assertFalse(StringUtils.isEquals("abc","ABC"));
}
