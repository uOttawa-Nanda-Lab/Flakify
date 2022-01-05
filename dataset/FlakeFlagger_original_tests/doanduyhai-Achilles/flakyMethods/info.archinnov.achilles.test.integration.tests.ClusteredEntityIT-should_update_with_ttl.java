@Test public void should_update_with_ttl() throws Exception {
  compoundKey=new ClusteredKey(RandomUtils.nextLong(),RandomUtils.nextInt(),"name");
  entity=new ClusteredEntity(compoundKey,"clustered_value");
  entity=manager.persist(entity,OptionsBuilder.withTtl(1));
  assertThat(manager.find(ClusteredEntity.class,compoundKey)).isNotNull();
  Thread.sleep(1000);
  assertThat(manager.find(ClusteredEntity.class,compoundKey)).isNull();
}
