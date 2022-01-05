@Test public void should_get_component_classes() throws Exception {
  PropertyMeta idMeta=PropertyMetaTestBuilder.valueClass(EmbeddedKey.class).compClasses(Long.class,String.class).build();
  assertThat(idMeta.getComponentClasses()).containsExactly(Long.class,String.class);
}
