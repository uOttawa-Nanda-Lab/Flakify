@Test public void should_set_partition_key_and_create_builder() throws Exception {
  Long partitionKey=RandomUtils.nextLong();
  SliceQueryBuilder<ClusteredEntity>.SliceShortcutQueryBuilder shortCutBuilder=builder.partitionComponents(partitionKey);
  assertThat(shortCutBuilder).isNotNull();
}
