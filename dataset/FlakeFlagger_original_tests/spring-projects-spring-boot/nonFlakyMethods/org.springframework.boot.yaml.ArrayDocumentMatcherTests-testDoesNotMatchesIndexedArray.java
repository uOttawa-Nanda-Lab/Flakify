@Test public void testDoesNotMatchesIndexedArray() throws IOException {
  ArrayDocumentMatcher matcher=new ArrayDocumentMatcher("foo","bar");
  assertEquals(MatchStatus.ABSTAIN,matcher.matches(getProperties("foo[0]: bar\nfoo[1]: spam")));
}
