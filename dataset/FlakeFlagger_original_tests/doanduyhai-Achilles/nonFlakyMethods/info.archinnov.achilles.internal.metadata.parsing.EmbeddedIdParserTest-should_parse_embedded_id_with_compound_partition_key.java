@Test public void should_parse_embedded_id_with_compound_partition_key() throws Exception {
  EmbeddedIdProperties props=parser.parseEmbeddedId(EmbeddedKeyWithCompoundPartitionKey.class);
  assertThat(props.isCompositePartitionKey()).isTrue();
  assertThat(props.getPartitionComponentNames()).containsExactly("id","type");
  assertThat(props.getPartitionComponentClasses()).containsExactly(Long.class,String.class);
  assertThat(props.getClusteringComponentNames()).containsExactly("date");
  assertThat(props.getClusteringComponentClasses()).containsExactly(UUID.class);
  assertThat(props.getComponentClasses()).containsExactly(Long.class,String.class,UUID.class);
  assertThat(props.getComponentNames()).containsExactly("id","type","date");
  assertThat(props.getComponentGetters()).hasSize(3);
  assertThat(props.getComponentSetters()).hasSize(3);
}
