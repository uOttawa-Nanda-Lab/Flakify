@Test public void should_find_entities_for_indexed_enum_query() throws Exception {
  EntityWithSecondaryIndexOnEnum entity1=new EntityWithSecondaryIndexOnEnum(10L,EACH_QUORUM);
  EntityWithSecondaryIndexOnEnum entity2=new EntityWithSecondaryIndexOnEnum(11L,EACH_QUORUM);
  EntityWithSecondaryIndexOnEnum entity3=new EntityWithSecondaryIndexOnEnum(13L,LOCAL_QUORUM);
  manager.persist(entity1);
  manager.persist(entity2);
  manager.persist(entity3);
  IndexCondition condition=new IndexCondition("consistencyLevel",EACH_QUORUM);
  final List<EntityWithSecondaryIndexOnEnum> actual=manager.indexedQuery(EntityWithSecondaryIndexOnEnum.class,condition).get();
  assertThat(actual).hasSize(2);
  final EntityWithSecondaryIndexOnEnum found1=actual.get(0);
  final EntityWithSecondaryIndexOnEnum found2=actual.get(1);
  assertThat(found1.getId()).isEqualTo(10L);
  assertThat(found2.getId()).isEqualTo(11L);
}
