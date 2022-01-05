@Test public void should_persist() throws Exception {
  Long id=RandomUtils.nextLong();
  EntityWithCompositePartitionKey entity=new EntityWithCompositePartitionKey(id,"type","value");
  manager.persist(entity);
  Row row=session.execute("SELECT * FROM " + TABLE_NAME + " WHERE id="+ id+ " AND type='type'").one();
  assertThat(row).isNotNull();
  assertThat(row.getLong("id")).isEqualTo(id);
  assertThat(row.getString("type")).isEqualTo("type");
  assertThat(row.getString("value")).isEqualTo("value");
}
