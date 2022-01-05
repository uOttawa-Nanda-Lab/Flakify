@Test public void should_duplicate_without_ttl_and_timestamp() throws Exception {
  final CASCondition CASCondition=new CASCondition("name","John");
  Options options=OptionsBuilder.withConsistency(EACH_QUORUM).withTtl(10).withTimestamp(100L).ifNotExists().ifConditions(CASCondition);
  Options newOptions=options.duplicateWithoutTtlAndTimestamp();
  assertThat(newOptions.getConsistencyLevel().get()).isSameAs(EACH_QUORUM);
  assertThat(newOptions.getTimestamp().isPresent()).isFalse();
  assertThat(newOptions.getTtl().isPresent()).isFalse();
  assertThat(newOptions.isIfNotExists()).isTrue();
  assertThat(newOptions.hasCasConditions()).isTrue();
  assertThat(newOptions.getCASConditions()).containsExactly(CASCondition);
}
