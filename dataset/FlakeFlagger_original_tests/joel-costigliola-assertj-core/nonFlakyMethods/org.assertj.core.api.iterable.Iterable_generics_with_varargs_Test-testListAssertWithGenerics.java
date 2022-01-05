@Test public void testListAssertWithGenerics() throws Exception {
  List<? extends String> strings=asList("a","b","c");
  assertThat(strings).contains("a","b");
}
