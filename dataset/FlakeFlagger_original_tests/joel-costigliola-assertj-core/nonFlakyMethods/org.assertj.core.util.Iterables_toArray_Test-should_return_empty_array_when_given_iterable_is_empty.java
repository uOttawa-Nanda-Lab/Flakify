@Test public void should_return_empty_array_when_given_iterable_is_empty() throws Exception {
  assertThat(Iterables.toArray(emptyList())).isEmpty();
}
