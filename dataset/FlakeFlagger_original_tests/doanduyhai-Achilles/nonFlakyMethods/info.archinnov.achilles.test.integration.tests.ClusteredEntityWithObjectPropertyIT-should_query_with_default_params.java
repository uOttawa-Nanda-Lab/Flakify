@Test public void should_query_with_default_params() throws Exception {
  long partitionKey=RandomUtils.nextLong();
  List<ClusteredEntityWithObjectValue> entities=manager.sliceQuery(ClusteredEntityWithObjectValue.class).partitionComponents(partitionKey).fromClusterings("name2").toClusterings("name4").get();
  assertThat(entities).isEmpty();
  insertValues(partitionKey,5);
  entities=manager.sliceQuery(ClusteredEntityWithObjectValue.class).partitionComponents(partitionKey).fromClusterings("name2").toClusterings("name4").get();
  assertThat(entities).hasSize(3);
  assertThat(entities.get(0).getValue().getContent()).isEqualTo("name2");
  assertThat(entities.get(0).getId().getId()).isEqualTo(partitionKey);
  assertThat(entities.get(0).getId().getName()).isEqualTo("name2");
  assertThat(entities.get(1).getValue().getContent()).isEqualTo("name3");
  assertThat(entities.get(1).getId().getId()).isEqualTo(partitionKey);
  assertThat(entities.get(1).getId().getName()).isEqualTo("name3");
  assertThat(entities.get(2).getValue().getContent()).isEqualTo("name4");
  assertThat(entities.get(2).getId().getId()).isEqualTo(partitionKey);
  assertThat(entities.get(2).getId().getName()).isEqualTo("name4");
  entities=manager.sliceQuery(ClusteredEntityWithObjectValue.class).fromEmbeddedId(new ClusteredKey(partitionKey,"name2")).toEmbeddedId(new ClusteredKey(partitionKey,"name4")).get();
  assertThat(entities).hasSize(3);
  assertThat(entities.get(0).getValue().getContent()).isEqualTo("name2");
  assertThat(entities.get(0).getId().getId()).isEqualTo(partitionKey);
  assertThat(entities.get(0).getId().getName()).isEqualTo("name2");
  assertThat(entities.get(1).getValue().getContent()).isEqualTo("name3");
  assertThat(entities.get(1).getId().getId()).isEqualTo(partitionKey);
  assertThat(entities.get(1).getId().getName()).isEqualTo("name3");
  assertThat(entities.get(2).getValue().getContent()).isEqualTo("name4");
  assertThat(entities.get(2).getId().getId()).isEqualTo(partitionKey);
  assertThat(entities.get(2).getId().getName()).isEqualTo("name4");
  ;
}
