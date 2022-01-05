@Test public void should_exception_when_embedded_id_has_no_annotation() throws Exception {
  exception.expect(AchillesBeanMappingException.class);
  exception.expectMessage("There should be at least 2 fields annotated with @Order for the @EmbeddedId class '" + EmbeddedKeyWithNoAnnotation.class.getCanonicalName() + "'");
  parser.parseEmbeddedId(EmbeddedKeyWithNoAnnotation.class);
}
