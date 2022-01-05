@Test public void should_update_modifications() throws Exception {
  compoundKey=new ClusteredKey(RandomUtils.nextLong(),Type.FILE);
  entity=new ClusteredEntityWithEnumCompoundKey(compoundKey,"clustered_value");
  entity=manager.persist(entity);
  entity.setValue("new_clustered_value");
  manager.update(entity);
  entity=manager.find(ClusteredEntityWithEnumCompoundKey.class,compoundKey);
  assertThat(entity.getValue()).isEqualTo("new_clustered_value");
}
