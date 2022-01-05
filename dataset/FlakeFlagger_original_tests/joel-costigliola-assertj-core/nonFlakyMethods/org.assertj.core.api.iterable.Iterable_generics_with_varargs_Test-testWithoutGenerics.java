@SuppressWarnings({"unchecked","rawtypes"}) @Test public void testWithoutGenerics() throws Exception {
  List strings=asList("a","b","c");
  assertThat(strings).contains("a","b");
}
