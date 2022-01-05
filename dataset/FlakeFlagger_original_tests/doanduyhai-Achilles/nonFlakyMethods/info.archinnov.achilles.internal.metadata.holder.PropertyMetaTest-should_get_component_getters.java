@Test public void should_get_component_getters() throws Exception {
  Method idGetter=EmbeddedKey.class.getDeclaredMethod("getUserId");
  Method nameGetter=EmbeddedKey.class.getDeclaredMethod("getName");
  PropertyMeta idMeta=PropertyMetaTestBuilder.valueClass(EmbeddedKey.class).compGetters(idGetter,nameGetter).build();
  assertThat(idMeta.getComponentGetters()).containsExactly(idGetter,nameGetter);
}
