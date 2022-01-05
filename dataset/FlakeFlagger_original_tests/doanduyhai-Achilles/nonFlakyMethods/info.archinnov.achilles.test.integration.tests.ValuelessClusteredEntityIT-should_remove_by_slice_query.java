@Test public void should_remove_by_slice_query() throws Exception {
  Long id=RandomUtils.nextLong();
  String name1="name1";
  String name2="name2";
  String name3="name3";
  manager.persist(new ValuelessClusteredEntity(new CompoundKey(id,name1)));
  manager.persist(new ValuelessClusteredEntity(new CompoundKey(id,name2)));
  manager.persist(new ValuelessClusteredEntity(new CompoundKey(id,name3)));
  manager.sliceQuery(ValuelessClusteredEntity.class).partitionComponents(id).fromClusterings(name2).toClusterings(name2).remove();
  List<ValuelessClusteredEntity> result=manager.sliceQuery(ValuelessClusteredEntity.class).partitionComponents(id).get();
  assertThat(result).hasSize(2);
  assertThat(result.get(0).getId().getName()).isEqualTo(name1);
  assertThat(result.get(1).getId().getName()).isEqualTo(name3);
}
