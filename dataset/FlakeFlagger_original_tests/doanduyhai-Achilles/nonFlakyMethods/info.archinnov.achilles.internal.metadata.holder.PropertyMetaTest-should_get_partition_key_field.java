@Test public void should_get_partition_key_field() throws Exception {
  Field idField=EmbeddedKey.class.getDeclaredField("userId");
  Field nameField=EmbeddedKey.class.getDeclaredField("name");
  PropertyMeta idMeta=PropertyMetaTestBuilder.valueClass(EmbeddedKey.class).compFields(idField,nameField).build();
  assertThat(idMeta.getPartitionKeyField()).isEqualTo(idField);
}
