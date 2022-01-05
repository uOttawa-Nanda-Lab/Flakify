@Test public void should_remove_with_default_params() throws Exception {
  long partitionKey=RandomUtils.nextLong();
  insertValues(partitionKey,3);
  manager.sliceQuery(ClusteredEntityWithCounter.class).partitionComponents(partitionKey).fromClusterings("name2").toClusterings("name2").remove();
  Thread.sleep(100);
  List<ClusteredEntityWithCounter> entities=manager.sliceQuery(ClusteredEntityWithCounter.class).partitionComponents(partitionKey).get(100);
  assertThat(entities).hasSize(2);
  assertThat(entities.get(0).getCounter().get()).isEqualTo(1L);
  assertThat(entities.get(0).getVersion().get()).isEqualTo(1L);
  assertThat(entities.get(1).getCounter().get()).isEqualTo(3L);
  assertThat(entities.get(1).getVersion().get()).isEqualTo(3L);
}
