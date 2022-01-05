@Test public void should_return_cql_functions_for_native_query() throws Exception {
  Long id=RandomUtils.nextLong();
  UUID date=UUIDGen.getTimeUUID();
  manager.persist(new ClusteredEntityWithTimeUUID(id,date,"value"));
  Map<String,Object> result=manager.nativeQuery("SELECT now(),dateOf(date),unixTimestampOf(date) FROM " + ClusteredEntityWithTimeUUID.TABLE_NAME + " WHERE id="+ id).first();
  assertThat(result.get("now()")).isNotNull().isInstanceOf(UUID.class);
  assertThat(result.get("dateOf(date)")).isNotNull().isInstanceOf(Date.class);
  assertThat(result.get("unixTimestampOf(date)")).isNotNull().isInstanceOf(Long.class);
}
