@Test public void should_not_consume_iterator_when_asserting_non_null() throws Exception {
  Iterator<?> iterator=mock(Iterator.class);
  assertThat(iterator).isNotNull();
  verifyZeroInteractions(iterator);
}
