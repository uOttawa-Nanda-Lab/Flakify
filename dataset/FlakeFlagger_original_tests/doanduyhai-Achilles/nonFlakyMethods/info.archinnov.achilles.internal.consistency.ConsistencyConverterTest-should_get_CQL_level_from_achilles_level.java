@Test public void should_get_CQL_level_from_achilles_level() throws Exception {
  assertThat(ConsistencyConverter.getCQLLevel(ConsistencyLevel.EACH_QUORUM)).isEqualTo(com.datastax.driver.core.ConsistencyLevel.EACH_QUORUM);
}
