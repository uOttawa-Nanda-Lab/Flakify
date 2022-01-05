@Test public void should_exception_when_embedded_id_incorrect_type() throws Exception {
  exception.expect(AchillesBeanMappingException.class);
  exception.expectMessage("The class 'java.util.List' is not a valid component type for the @EmbeddedId class '" + EmbeddedKeyIncorrectType.class.getCanonicalName() + "'");
  parser.parseEmbeddedId(EmbeddedKeyIncorrectType.class);
}
