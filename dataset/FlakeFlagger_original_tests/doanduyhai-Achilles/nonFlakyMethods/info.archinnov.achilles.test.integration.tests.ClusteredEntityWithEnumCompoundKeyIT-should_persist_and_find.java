@Test public void should_persist_and_find() throws Exception {
  compoundKey=new ClusteredKey(RandomUtils.nextLong(),Type.AUDIO);
  entity=new ClusteredEntityWithEnumCompoundKey(compoundKey,"clustered_value");
  manager.persist(entity);
  ClusteredEntityWithEnumCompoundKey found=manager.find(ClusteredEntityWithEnumCompoundKey.class,compoundKey);
  assertThat(found.getId()).isEqualTo(compoundKey);
  assertThat(found.getValue()).isEqualTo("clustered_value");
}
