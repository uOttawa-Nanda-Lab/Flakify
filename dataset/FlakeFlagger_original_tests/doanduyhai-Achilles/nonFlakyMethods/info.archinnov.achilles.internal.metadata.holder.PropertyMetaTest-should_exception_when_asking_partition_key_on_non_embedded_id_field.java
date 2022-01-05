@Test public void should_exception_when_asking_partition_key_on_non_embedded_id_field() throws Exception {
  EmbeddedKey embeddedKey=new EmbeddedKey();
  PropertyMeta pm=new PropertyMeta();
  pm.setPropertyName("property");
  pm.setType(SIMPLE);
  exception.expect(IllegalStateException.class);
  exception.expectMessage("Cannot get partition key on a non embedded id field 'property'");
  pm.getPartitionKey(embeddedKey);
}
