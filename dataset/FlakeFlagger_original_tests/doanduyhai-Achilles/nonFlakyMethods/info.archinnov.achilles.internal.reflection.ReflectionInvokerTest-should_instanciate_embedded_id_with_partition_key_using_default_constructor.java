@Test public void should_instanciate_embedded_id_with_partition_key_using_default_constructor() throws Exception {
  Long partitionKey=RandomUtils.nextLong();
  Field userIdField=EmbeddedKey.class.getDeclaredField("userId");
  PropertyMeta idMeta=PropertyMetaTestBuilder.valueClass(EmbeddedKey.class).compFields(userIdField).build();
  Object actual=invoker.instantiateEmbeddedIdWithPartitionComponents(idMeta,Arrays.<Object>asList(partitionKey));
  assertThat(actual).isNotNull();
  EmbeddedKey embeddedKey=(EmbeddedKey)actual;
  assertThat(embeddedKey.getUserId()).isEqualTo(partitionKey);
  assertThat(embeddedKey.getName()).isNull();
}
