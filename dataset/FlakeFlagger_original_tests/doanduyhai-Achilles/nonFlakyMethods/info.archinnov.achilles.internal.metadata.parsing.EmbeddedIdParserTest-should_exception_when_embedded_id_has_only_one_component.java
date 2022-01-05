@Test public void should_exception_when_embedded_id_has_only_one_component() throws Exception {
  exception.expect(AchillesBeanMappingException.class);
  exception.expectMessage("There should be at least 2 fields annotated with @Order for the @EmbeddedId class '" + EmbeddedKeyWithOnlyOneComponent.class.getCanonicalName() + "'");
  parser.parseEmbeddedId(EmbeddedKeyWithOnlyOneComponent.class);
}
