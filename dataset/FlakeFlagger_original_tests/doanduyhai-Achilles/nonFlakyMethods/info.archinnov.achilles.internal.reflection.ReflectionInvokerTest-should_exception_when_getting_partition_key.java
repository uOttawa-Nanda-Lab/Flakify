@Test public void should_exception_when_getting_partition_key() throws Exception {
  Field userIdField=EmbeddedKey.class.getDeclaredField("userId");
  PropertyMeta idMeta=PropertyMetaTestBuilder.valueClass(EmbeddedKey.class).compFields(userIdField).type(PropertyType.EMBEDDED_ID).build();
  exception.expect(AchillesException.class);
  exception.expectMessage("Cannot get partition key from field 'userId' of type '" + EmbeddedKey.class.getCanonicalName() + "' from compoundKey 'compound'");
  invoker.getPartitionKey("compound",idMeta);
}
