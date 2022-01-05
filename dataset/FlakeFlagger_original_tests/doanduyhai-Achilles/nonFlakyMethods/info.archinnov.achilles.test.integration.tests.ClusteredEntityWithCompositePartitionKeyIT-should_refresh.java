@Test public void should_refresh() throws Exception {
  long id=RandomUtils.nextLong();
  Integer index=11;
  compoundKey=new EmbeddedKey(id,"type",index);
  entity=new ClusteredEntityWithCompositePartitionKey(id,"type",index,"clustered_value");
  entity=manager.persist(entity);
  session.execute("UPDATE " + TABLE_NAME + " SET value='new_clustered_value' WHERE id="+ id+ " AND type='type' AND indexes=11");
  manager.refresh(entity);
  assertThat(entity.getValue()).isEqualTo("new_clustered_value");
}
