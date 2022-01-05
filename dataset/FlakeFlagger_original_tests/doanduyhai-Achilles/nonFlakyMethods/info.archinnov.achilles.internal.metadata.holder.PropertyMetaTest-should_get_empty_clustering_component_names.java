@Test public void should_get_empty_clustering_component_names() throws Exception {
  PropertyMeta pm=PropertyMetaTestBuilder.valueClass(String.class).build();
  assertThat(pm.getClusteringComponentNames()).isEmpty();
}
