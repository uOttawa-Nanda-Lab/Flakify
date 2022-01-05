@Test public void should_refresh() throws Exception {
  long partitionKey=RandomUtils.nextLong();
  compoundKey=new ClusteredKey(partitionKey,Type.FILE);
  entity=new ClusteredEntityWithEnumCompoundKey(compoundKey,"clustered_value");
  entity=manager.persist(entity);
  session.execute("UPDATE " + TABLE_NAME + " set value='new_clustered_value' where id="+ partitionKey+ " and type = 'FILE'");
  manager.refresh(entity);
  assertThat(entity.getValue()).isEqualTo("new_clustered_value");
}
