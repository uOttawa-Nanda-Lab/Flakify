@Test public void should_get_read_consistency() throws Exception {
  PropertyMeta pm=new PropertyMeta();
  assertThat(pm.getReadConsistencyLevel()).isNull();
  assertThat(pm.getWriteConsistencyLevel()).isNull();
  pm.setConsistencyLevels(Pair.<ConsistencyLevel,ConsistencyLevel>create(QUORUM,QUORUM));
  assertThat(pm.getReadConsistencyLevel()).isEqualTo(QUORUM);
  assertThat(pm.getWriteConsistencyLevel()).isEqualTo(QUORUM);
}
