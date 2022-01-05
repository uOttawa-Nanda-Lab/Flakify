@Test public void should_update_modifications() throws Exception {
  compoundKey=new ClusteredKey(RandomUtils.nextLong(),RandomUtils.nextInt(),"name");
  entity=new ClusteredEntity(compoundKey,"clustered_value");
  entity=manager.persist(entity);
  entity.setValue("new_clustered_value");
  manager.update(entity);
  entity=manager.find(ClusteredEntity.class,compoundKey);
  assertThat(entity.getValue()).isEqualTo("new_clustered_value");
}
