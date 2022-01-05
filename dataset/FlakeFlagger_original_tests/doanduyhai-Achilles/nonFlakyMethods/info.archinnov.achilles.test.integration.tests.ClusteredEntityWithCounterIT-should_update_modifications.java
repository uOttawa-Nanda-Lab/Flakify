@Test public void should_update_modifications() throws Exception {
  long initialValue=RandomUtils.nextLong();
  long increment=RandomUtils.nextLong();
  compoundKey=new ClusteredKey(RandomUtils.nextLong(),"name");
  entity=new ClusteredEntityWithCounter(compoundKey,incr(initialValue),incr(initialValue));
  entity=manager.persist(entity);
  assertThat(entity.getCounter().get()).isEqualTo(initialValue);
  entity.getCounter().incr(increment);
  entity.getVersion().incr(increment);
  manager.update(entity);
  entity=manager.find(ClusteredEntityWithCounter.class,compoundKey);
  assertThat(entity.getCounter().get()).isEqualTo(initialValue + increment);
  assertThat(entity.getVersion().get()).isEqualTo(initialValue + increment);
}
