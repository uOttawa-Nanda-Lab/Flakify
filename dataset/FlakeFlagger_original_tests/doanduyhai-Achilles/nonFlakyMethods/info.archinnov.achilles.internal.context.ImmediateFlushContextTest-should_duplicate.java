@Test public void should_duplicate() throws Exception {
  context=new ImmediateFlushContext(daoContext,LOCAL_QUORUM,Optional.fromNullable(ConsistencyLevel.LOCAL_SERIAL));
  ImmediateFlushContext actual=context.duplicate();
  assertThat(actual.consistencyLevel).isEqualTo(LOCAL_QUORUM);
  assertThat(actual.serialConsistencyLevel.get()).isEqualTo(ConsistencyLevel.LOCAL_SERIAL);
}
