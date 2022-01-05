@Test public void should_refresh() throws Exception {
  long partitionKey=RandomUtils.nextLong();
  int count=RandomUtils.nextInt();
  String name="name";
  compoundKey=new ClusteredKey(partitionKey,count,name);
  entity=new ClusteredEntity(compoundKey,"clustered_value");
  entity=manager.persist(entity);
  session.execute("update " + TABLE_NAME + " set value='new_clustered_value' where id="+ partitionKey+ " and count="+ count+ " and name='"+ name+ "'");
  manager.refresh(entity);
  assertThat(entity.getValue()).isEqualTo("new_clustered_value");
}
