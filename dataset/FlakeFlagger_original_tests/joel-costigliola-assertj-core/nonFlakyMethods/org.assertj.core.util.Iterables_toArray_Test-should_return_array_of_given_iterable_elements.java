@Test public void should_return_array_of_given_iterable_elements() throws Exception {
  assertThat(Iterables.toArray(values)).containsExactly("one","two");
}
