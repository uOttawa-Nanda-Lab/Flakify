@Test public void should_exception_when_embedded_id_no_pulic_default_constructor() throws Exception {
  exception.expect(AchillesBeanMappingException.class);
  exception.expectMessage("The @EmbeddedId class '" + EmbeddedKeyNotInstantiable.class.getCanonicalName() + "' should have a public default constructor");
  parser.parseEmbeddedId(EmbeddedKeyNotInstantiable.class);
}
