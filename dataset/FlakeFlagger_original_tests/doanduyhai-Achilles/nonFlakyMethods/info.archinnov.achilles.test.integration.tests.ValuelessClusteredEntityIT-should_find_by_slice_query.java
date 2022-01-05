@Test public void should_find_by_slice_query() throws Exception {
  Long id=RandomUtils.nextLong();
  String name1="name1";
  String name2="name2";
  String name3="name3";
  String name4="name4";
  String name5="name5";
  manager.persist(new ValuelessClusteredEntity(new CompoundKey(id,name1)));
  manager.persist(new ValuelessClusteredEntity(new CompoundKey(id,name2)));
  manager.persist(new ValuelessClusteredEntity(new CompoundKey(id,name3)));
  manager.persist(new ValuelessClusteredEntity(new CompoundKey(id,name4)));
  manager.persist(new ValuelessClusteredEntity(new CompoundKey(id,name5)));
  List<ValuelessClusteredEntity> result=manager.sliceQuery(ValuelessClusteredEntity.class).partitionComponents(id).fromClusterings(name5).toClusterings(name2).bounding(BoundingMode.INCLUSIVE_START_BOUND_ONLY).ordering(OrderingMode.DESCENDING).limit(3).get();
  assertThat(result).hasSize(3);
  assertThat(result.get(0).getId().getName()).isEqualTo(name5);
  assertThat(result.get(1).getId().getName()).isEqualTo(name4);
  assertThat(result.get(2).getId().getName()).isEqualTo(name3);
}
