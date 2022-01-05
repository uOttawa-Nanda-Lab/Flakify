@Test public void should_get_clustering_component_classes() throws Exception {
  PropertyMeta pm=PropertyMetaTestBuilder.valueClass(String.class).compClasses(Long.class,UUID.class,String.class).build();
  assertThat(pm.getClusteringComponentClasses()).containsExactly(UUID.class,String.class);
}
