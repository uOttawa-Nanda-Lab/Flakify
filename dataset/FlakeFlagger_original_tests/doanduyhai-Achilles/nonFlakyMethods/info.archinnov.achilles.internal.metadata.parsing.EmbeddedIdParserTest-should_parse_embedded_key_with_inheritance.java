@Test public void should_parse_embedded_key_with_inheritance() throws Exception {
  EmbeddedIdProperties props=parser.parseEmbeddedId(EmbeddedKeyChild1.class);
  assertThat(props.isCompositePartitionKey()).isFalse();
  assertThat(props.getPartitionComponentNames()).containsExactly("partition_key");
  assertThat(props.getPartitionComponentClasses()).containsExactly(String.class);
  assertThat(props.getClusteringComponentNames()).containsExactly("clustering");
  assertThat(props.getClusteringComponentClasses()).containsExactly(Long.class);
  assertThat(props.getComponentClasses()).containsExactly(String.class,Long.class);
  assertThat(props.getComponentNames()).containsExactly("partition_key","clustering");
  assertThat(props.getComponentGetters()).hasSize(2);
  assertThat(props.getComponentSetters()).hasSize(2);
}
