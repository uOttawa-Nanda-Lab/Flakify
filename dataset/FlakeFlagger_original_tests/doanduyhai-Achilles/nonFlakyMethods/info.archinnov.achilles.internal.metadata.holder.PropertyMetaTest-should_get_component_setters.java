@Test public void should_get_component_setters() throws Exception {
  Method idSetter=EmbeddedKey.class.getDeclaredMethod("setUserId",Long.class);
  Method nameSetter=EmbeddedKey.class.getDeclaredMethod("setName",String.class);
  PropertyMeta idMeta=PropertyMetaTestBuilder.valueClass(EmbeddedKey.class).compSetters(idSetter,nameSetter).build();
  assertThat(idMeta.getComponentSetters()).containsExactly(idSetter,nameSetter);
}
