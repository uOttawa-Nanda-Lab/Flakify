@Test public void should_exception_when_embedded_id_wrong_key_order() throws Exception {
  exception.expect(AchillesBeanMappingException.class);
  exception.expectMessage("The component ordering is wrong for @EmbeddedId class '" + EmbeddedKeyWithNegativeOrder.class.getCanonicalName() + "'");
  parser.parseEmbeddedId(EmbeddedKeyWithNegativeOrder.class);
}
