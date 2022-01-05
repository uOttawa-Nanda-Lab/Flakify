@Test public void should_return_null_for_partition_key_if_not_embedded_id() throws Exception {
  long partitionKey=RandomUtils.nextLong();
  Field userIdField=EmbeddedKey.class.getDeclaredField("userId");
  PropertyMeta idMeta=PropertyMetaTestBuilder.valueClass(EmbeddedKey.class).compFields(userIdField).type(PropertyType.ID).build();
  EmbeddedKey embeddedKey=new EmbeddedKey(partitionKey,"name");
  assertThat(invoker.getPartitionKey(embeddedKey,idMeta)).isNull();
}
