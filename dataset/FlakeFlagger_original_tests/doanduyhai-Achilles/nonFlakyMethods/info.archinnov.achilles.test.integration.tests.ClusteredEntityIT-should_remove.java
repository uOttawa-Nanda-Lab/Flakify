@Test public void should_remove() throws Exception {
  compoundKey=new ClusteredKey(RandomUtils.nextLong(),RandomUtils.nextInt(),"name");
  entity=new ClusteredEntity(compoundKey,"clustered_value");
  entity=manager.persist(entity);
  manager.remove(entity);
  assertThat(manager.find(ClusteredEntity.class,compoundKey)).isNull();
}
