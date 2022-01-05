@Test public void should_return_empty_list_when_no_component_setters() throws Exception {
  PropertyMeta idMeta=PropertyMetaTestBuilder.valueClass(EmbeddedKey.class).build();
  assertThat(idMeta.getComponentSetters()).isEmpty();
}
