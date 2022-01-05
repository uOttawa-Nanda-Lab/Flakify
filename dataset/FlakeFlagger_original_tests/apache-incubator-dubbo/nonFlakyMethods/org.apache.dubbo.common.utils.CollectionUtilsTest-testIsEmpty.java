@Test public void testIsEmpty() throws Exception {
  assertThat(isEmpty(null),is(true));
  assertThat(isEmpty(new HashSet()),is(true));
  assertThat(isEmpty(emptyList()),is(true));
}
