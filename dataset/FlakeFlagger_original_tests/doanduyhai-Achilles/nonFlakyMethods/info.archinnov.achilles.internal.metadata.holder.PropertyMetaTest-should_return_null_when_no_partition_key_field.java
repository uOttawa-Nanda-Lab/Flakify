@Test public void should_return_null_when_no_partition_key_field() throws Exception {
  PropertyMeta idMeta=PropertyMetaTestBuilder.valueClass(EmbeddedKey.class).build();
  assertThat(idMeta.getPartitionKeyField()).isNull();
}
