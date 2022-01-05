@Test public void testIsNotEmpty() throws Exception {
  assertThat(isNotEmpty(singleton("a")),is(true));
}
