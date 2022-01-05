@Test public void should_get_clustering_component_names() throws Exception {
  PropertyMeta pm=PropertyMetaTestBuilder.valueClass(String.class).compNames("id","comp1","comp2").build();
  assertThat(pm.getClusteringComponentNames()).containsExactly("comp1","comp2");
}
