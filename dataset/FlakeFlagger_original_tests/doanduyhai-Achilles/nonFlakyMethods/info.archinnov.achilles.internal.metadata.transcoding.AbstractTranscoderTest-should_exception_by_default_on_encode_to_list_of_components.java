@Test public void should_exception_by_default_on_encode_to_list_of_components() throws Exception {
  PropertyMeta pm=PropertyMetaTestBuilder.valueClass(EmbeddedKey.class).type(SIMPLE).build();
  exception.expect(AchillesException.class);
  exception.expectMessage("Transcoder cannot encode components value '[]'");
  transcoder.encodeToComponents(pm,Arrays.asList());
}
