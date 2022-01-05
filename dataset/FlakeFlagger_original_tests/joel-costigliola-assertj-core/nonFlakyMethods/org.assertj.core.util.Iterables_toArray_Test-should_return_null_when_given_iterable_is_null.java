@Test public void should_return_null_when_given_iterable_is_null() throws Exception {
  assertThat(Iterables.toArray(null)).isNull();
}
