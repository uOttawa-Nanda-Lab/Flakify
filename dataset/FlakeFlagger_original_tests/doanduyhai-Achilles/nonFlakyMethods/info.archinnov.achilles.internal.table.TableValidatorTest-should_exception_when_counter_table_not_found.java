@Test public void should_exception_when_counter_table_not_found() throws Exception {
  KeyspaceMetadata keyspaceMeta=mock(KeyspaceMetadata.class);
  when(cluster.getMetadata().getKeyspace(keyspaceName)).thenReturn(keyspaceMeta);
  exception.expect(AchillesInvalidTableException.class);
  exception.expectMessage("Cannot find table '" + CQL_COUNTER_TABLE + "' from keyspace '"+ keyspaceName+ "'");
  validator.validateAchillesCounter(keyspaceMeta,keyspaceName);
}
