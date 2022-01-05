@Test public void should_remove() throws Exception {
  compoundKey=new ClusteredKey(RandomUtils.nextLong(),Type.IMAGE);
  entity=new ClusteredEntityWithEnumCompoundKey(compoundKey,"clustered_value");
  entity=manager.persist(entity);
  manager.remove(entity);
  assertThat(manager.find(ClusteredEntityWithEnumCompoundKey.class,compoundKey)).isNull();
}
