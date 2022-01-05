@Test public void should_get_empty_component_names() throws Exception {
  PropertyMeta idMeta=PropertyMetaTestBuilder.valueClass(EmbeddedKey.class).build();
  assertThat(idMeta.getComponentNames()).isEmpty();
}
