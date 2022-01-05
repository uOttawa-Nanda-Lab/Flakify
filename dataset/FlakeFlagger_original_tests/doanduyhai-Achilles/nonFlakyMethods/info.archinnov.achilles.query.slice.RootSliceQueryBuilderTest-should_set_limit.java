@Test public void should_set_limit() throws Exception {
  builder.partitionComponentsInternal(10L).limit(53);
  assertThat(builder.buildClusterQuery().getLimit()).isEqualTo(53);
}
