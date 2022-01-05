@Test public void should_get_component_names() throws Exception {
  PropertyMeta idMeta=PropertyMetaTestBuilder.valueClass(EmbeddedKey.class).compNames("a","b").build();
  assertThat(idMeta.getComponentNames()).containsExactly("a","b");
}
