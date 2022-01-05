@Test public void should_refresh() throws Exception {
  long counterValue=RandomUtils.nextLong();
  long incr=RandomUtils.nextLong();
  long partitionKey=RandomUtils.nextLong();
  String name="name";
  compoundKey=new ClusteredKey(partitionKey,name);
  entity=new ClusteredEntityWithCounter(compoundKey,incr(counterValue));
  entity=manager.persist(entity);
  session.execute("UPDATE " + TABLE_NAME + " SET counter = counter + "+ incr+ " WHERE id="+ partitionKey+ " AND name='name'");
  session.execute("UPDATE " + TABLE_NAME + " SET version = version + "+ incr+ " WHERE id="+ partitionKey+ " AND name='name'");
  Thread.sleep(100);
  manager.refresh(entity);
  assertThat(entity.getCounter().get()).isEqualTo(counterValue + incr);
  assertThat(entity.getVersion().get()).isEqualTo(incr);
}
