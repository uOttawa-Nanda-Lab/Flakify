@Test public void should_refresh() throws Exception {
  long partitionKey=RandomUtils.nextLong();
  compoundKey=new ClusteredKey(partitionKey,"name");
  Holder holder=new Holder("content");
  Holder newHolder=new Holder("new_content");
  entity=new ClusteredEntityWithObjectValue(compoundKey,holder);
  entity=manager.persist(entity);
  session.execute("UPDATE " + TABLE_NAME + " SET value='"+ mapper.writeValueAsString(newHolder)+ "' where id="+ partitionKey+ " and name='name'");
  manager.refresh(entity);
  assertThat(entity.getValue()).isEqualTo(newHolder);
}
