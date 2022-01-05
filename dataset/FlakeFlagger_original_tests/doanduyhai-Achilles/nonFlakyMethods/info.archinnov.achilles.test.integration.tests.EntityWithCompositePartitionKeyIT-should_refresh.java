@Test public void should_refresh() throws Exception {
  long id=RandomUtils.nextLong();
  EntityWithCompositePartitionKey entity=new EntityWithCompositePartitionKey(id,"type","value");
  entity=manager.persist(entity);
  session.execute("UPDATE " + TABLE_NAME + " SET value='new_value' WHERE id="+ id+ " AND type='type'");
  manager.refresh(entity);
  assertThat(entity.getValue()).isEqualTo("new_value");
}
