@Test public void should_exception_by_default_on_encode_to_components() throws Exception {
  EmbeddedKey compound=new EmbeddedKey();
  PropertyMeta pm=PropertyMetaTestBuilder.valueClass(EmbeddedKey.class).type(SIMPLE).build();
  exception.expect(AchillesException.class);
  exception.expectMessage("Transcoder cannot encode from value '" + compound + "' to components for type '"+ pm.type().name()+ "'");
  transcoder.encodeToComponents(pm,compound);
}
