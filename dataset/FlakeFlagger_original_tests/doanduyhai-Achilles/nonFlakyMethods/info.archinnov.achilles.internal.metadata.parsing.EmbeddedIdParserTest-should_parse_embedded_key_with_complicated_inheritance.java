@Test public void should_parse_embedded_key_with_complicated_inheritance() throws Exception {
  EmbeddedIdProperties props=parser.parseEmbeddedId(EmbeddedKeyChild3.class);
  assertThat(props.isCompositePartitionKey()).isTrue();
  assertThat(props.getPartitionComponentNames()).containsExactly("partition_key","partition_key2");
  assertThat(props.getPartitionComponentClasses()).containsExactly(String.class,Long.class);
  assertThat(props.getClusteringComponentNames()).containsExactly("clustering");
  assertThat(props.getClusteringComponentClasses()).containsExactly(UUID.class);
  assertThat(props.getComponentClasses()).containsExactly(String.class,Long.class,UUID.class);
  assertThat(props.getComponentNames()).containsExactly("partition_key","partition_key2","clustering");
  assertThat(props.getComponentGetters()).hasSize(3);
  assertThat(props.getComponentSetters()).hasSize(3);
}
