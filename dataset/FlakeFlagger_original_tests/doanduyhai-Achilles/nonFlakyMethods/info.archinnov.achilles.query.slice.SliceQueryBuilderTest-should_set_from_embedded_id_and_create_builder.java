@Test public void should_set_from_embedded_id_and_create_builder() throws Exception {
  Long partitionKey=RandomUtils.nextLong();
  String name="name";
  EmbeddedKey embeddedKey=new EmbeddedKey(partitionKey,name);
  List<Object> components=Arrays.<Object>asList(partitionKey,name);
  when(transcoder.encodeToComponents(idMeta,embeddedKey)).thenReturn(components);
  SliceQueryBuilder<ClusteredEntity>.SliceFromEmbeddedIdBuilder embeddedIdBuilder=builder.fromEmbeddedId(embeddedKey);
  assertThat(embeddedIdBuilder).isNotNull();
}
