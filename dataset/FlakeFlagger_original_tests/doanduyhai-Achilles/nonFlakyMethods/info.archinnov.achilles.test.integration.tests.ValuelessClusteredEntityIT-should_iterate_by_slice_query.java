@Test public void should_iterate_by_slice_query() throws Exception {
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
  Iterator<ValuelessClusteredEntity> iterator=manager.sliceQuery(ValuelessClusteredEntity.class).partitionComponents(id).fromClusterings(name5).toClusterings(name2).bounding(BoundingMode.INCLUSIVE_START_BOUND_ONLY).ordering(OrderingMode.DESCENDING).iterator();
  assertThat(iterator.hasNext()).isTrue();
  assertThat(iterator.next().getId().getName()).isEqualTo(name5);
  assertThat(iterator.next().getId().getName()).isEqualTo(name4);
  assertThat(iterator.next().getId().getName()).isEqualTo(name3);
  assertThat(iterator.hasNext()).isFalse();
}
