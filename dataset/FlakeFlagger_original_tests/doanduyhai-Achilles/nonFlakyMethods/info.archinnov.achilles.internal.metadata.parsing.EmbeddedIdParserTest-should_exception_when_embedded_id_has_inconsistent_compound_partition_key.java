@Test public void should_exception_when_embedded_id_has_inconsistent_compound_partition_key() throws Exception {
  exception.expect(AchillesBeanMappingException.class);
  exception.expectMessage("The composite partition key ordering is wrong for @EmbeddedId class '" + EmbeddedKeyWithInconsistentCompoundPartitionKey.class.getCanonicalName() + "'");
  parser.parseEmbeddedId(EmbeddedKeyWithInconsistentCompoundPartitionKey.class);
}
