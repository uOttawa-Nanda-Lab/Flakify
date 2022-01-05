@Test public void should_parse_embedded_id() throws Exception {
  Method nameGetter=CorrectEmbeddedKey.class.getMethod("getName");
  Method nameSetter=CorrectEmbeddedKey.class.getMethod("setName",String.class);
  Method rankGetter=CorrectEmbeddedKey.class.getMethod("getRank");
  Method rankSetter=CorrectEmbeddedKey.class.getMethod("setRank",int.class);
  EmbeddedIdProperties props=parser.parseEmbeddedId(CorrectEmbeddedKey.class);
  assertThat(props.getComponentGetters()).containsExactly(nameGetter,rankGetter);
  assertThat(props.getComponentSetters()).containsExactly(nameSetter,rankSetter);
  assertThat(props.getComponentClasses()).containsExactly(String.class,int.class);
  assertThat(props.getComponentNames()).containsExactly("name","rank");
  assertThat(props.getOrderingComponent()).isEqualTo("rank");
  assertThat(props.getClusteringComponentNames()).containsExactly("rank");
  assertThat(props.getClusteringComponentClasses()).containsExactly(int.class);
  assertThat(props.getPartitionComponentNames()).containsExactly("name");
  assertThat(props.getPartitionComponentClasses()).containsExactly(String.class);
}
