@Test public void should_duplicate_without_ttl() throws Exception {
  context.statementWrappers.add(bsWrapper);
  BatchingFlushContext duplicate=context.duplicate();
  assertThat(duplicate.statementWrappers).containsOnly(bsWrapper);
  assertThat(duplicate.consistencyLevel).isSameAs(EACH_QUORUM);
  assertThat(duplicate.serialConsistencyLevel.isPresent()).isFalse();
}
