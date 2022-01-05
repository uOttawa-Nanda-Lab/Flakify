@Test public void should_exception_by_default_on_decode_from_components() throws Exception {
  List<Object> components=new ArrayList<Object>();
  PropertyMeta pm=PropertyMetaTestBuilder.valueClass(EmbeddedKey.class).type(SIMPLE).build();
  exception.expect(AchillesException.class);
  exception.expectMessage("Transcoder cannot decode from components '" + components + "' to value for type '"+ pm.type().name()+ "'");
  transcoder.decodeFromComponents(pm,components);
}
