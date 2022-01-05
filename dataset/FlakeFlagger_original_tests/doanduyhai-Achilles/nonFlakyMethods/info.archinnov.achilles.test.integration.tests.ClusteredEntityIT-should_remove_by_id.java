@Test public void should_remove_by_id() throws Exception {
  compoundKey=new ClusteredKey(RandomUtils.nextLong(),RandomUtils.nextInt(),"name");
  entity=new ClusteredEntity(compoundKey,"clustered_value");
  entity=manager.persist(entity);
  manager.removeById(ClusteredEntity.class,entity.getId());
  assertThat(manager.find(ClusteredEntity.class,compoundKey)).isNull();
}
