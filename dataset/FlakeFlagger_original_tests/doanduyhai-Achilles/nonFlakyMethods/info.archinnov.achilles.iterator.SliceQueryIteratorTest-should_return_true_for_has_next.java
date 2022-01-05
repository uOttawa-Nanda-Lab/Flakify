@Test public void should_return_true_for_has_next() throws Exception {
  when(iterator.hasNext()).thenReturn(true);
  assertThat(sliceIterator.hasNext()).isTrue();
}
