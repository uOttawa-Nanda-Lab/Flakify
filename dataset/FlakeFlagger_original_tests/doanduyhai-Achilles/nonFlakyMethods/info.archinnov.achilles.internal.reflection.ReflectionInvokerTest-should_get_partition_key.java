@Test public void should_get_partition_key() throws Exception {
  long partitionKey=RandomUtils.nextLong();
  Field userIdField=EmbeddedKey.class.getDeclaredField("userId");
  PropertyMeta idMeta=PropertyMetaTestBuilder.valueClass(EmbeddedKey.class).compFields(userIdField).type(PropertyType.EMBEDDED_ID).build();
  EmbeddedKey embeddedKey=new EmbeddedKey(partitionKey,"name");
  assertThat(invoker.getPartitionKey(embeddedKey,idMeta)).isEqualTo(partitionKey);
}
