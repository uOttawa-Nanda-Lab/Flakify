@Test public void should_parse_embedded_id_as_compound_partition_key() throws Exception {
  EmbeddedIdProperties props=parser.parseEmbeddedId(EmbeddedKeyAsCompoundPartitionKey.class);
  assertThat(props.isCompositePartitionKey()).isTrue();
  assertThat(props.getPartitionComponentNames()).containsExactly("id","type");
  assertThat(props.getPartitionComponentClasses()).containsExactly(Long.class,String.class);
  assertThat(props.getClusteringComponentNames()).isEmpty();
  assertThat(props.getClusteringComponentClasses()).isEmpty();
  assertThat(props.getComponentClasses()).containsExactly(Long.class,String.class);
  assertThat(props.getComponentNames()).containsExactly("id","type");
  assertThat(props.getComponentGetters()).hasSize(2);
  assertThat(props.getComponentSetters()).hasSize(2);
}
