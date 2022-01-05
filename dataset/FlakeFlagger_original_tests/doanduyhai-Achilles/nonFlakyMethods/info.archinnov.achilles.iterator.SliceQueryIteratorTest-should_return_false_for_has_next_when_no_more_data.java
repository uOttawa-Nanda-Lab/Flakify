@Test public void should_return_false_for_has_next_when_no_more_data() throws Exception {
  when(iterator.hasNext()).thenReturn(false);
  assertThat(sliceIterator.hasNext()).isFalse();
}
