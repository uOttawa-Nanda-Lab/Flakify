@Test public void should_exception_when_embedded_id_has_duplicate_order() throws Exception {
  exception.expect(AchillesBeanMappingException.class);
  exception.expectMessage("The order '1' is duplicated in @EmbeddedId class '" + EmbeddedKeyWithDuplicateOrder.class.getCanonicalName() + "'");
  parser.parseEmbeddedId(EmbeddedKeyWithDuplicateOrder.class);
}
