@Test public void testMatchesSingleValue() throws IOException {
  ArrayDocumentMatcher matcher=new ArrayDocumentMatcher("foo","bar");
  assertEquals(MatchStatus.FOUND,matcher.matches(getProperties("foo: bar")));
}
