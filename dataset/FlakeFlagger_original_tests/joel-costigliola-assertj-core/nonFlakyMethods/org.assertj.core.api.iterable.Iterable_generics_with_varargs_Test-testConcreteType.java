@Test public void testConcreteType() throws Exception {
  List<String> strings=asList("a","b","c");
  assertThat(strings).contains("a","b");
}
