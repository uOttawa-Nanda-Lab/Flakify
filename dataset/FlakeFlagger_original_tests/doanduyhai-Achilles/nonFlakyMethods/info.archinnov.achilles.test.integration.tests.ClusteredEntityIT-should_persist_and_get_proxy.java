@Test public void should_persist_and_get_proxy() throws Exception {
  compoundKey=new ClusteredKey(RandomUtils.nextLong(),RandomUtils.nextInt(),"name");
  entity=new ClusteredEntity(compoundKey,"clustered_value");
  manager.persist(entity);
  ClusteredEntity found=manager.getProxy(ClusteredEntity.class,compoundKey);
  assertThat(found.getId()).isEqualTo(compoundKey);
  assertThat(found.getValue()).isEqualTo("clustered_value");
}
