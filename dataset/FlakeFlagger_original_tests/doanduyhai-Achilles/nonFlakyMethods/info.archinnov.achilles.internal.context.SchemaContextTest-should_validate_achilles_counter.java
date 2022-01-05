@Test public void should_validate_achilles_counter() throws Exception {
  KeyspaceMetadata keyspaceMeta=mock(KeyspaceMetadata.class);
  when(cluster.getMetadata().getKeyspace(keyspaceName)).thenReturn(keyspaceMeta);
  context.validateAchillesCounter();
  verify(tableValidator).validateAchillesCounter(keyspaceMeta,keyspaceName);
}
