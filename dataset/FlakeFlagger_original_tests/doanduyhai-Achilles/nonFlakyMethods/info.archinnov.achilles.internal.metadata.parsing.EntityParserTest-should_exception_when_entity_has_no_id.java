@Test public void should_exception_when_entity_has_no_id() throws Exception {
  initEntityParsingContext(BeanWithNoId.class);
  expectedEx.expect(AchillesBeanMappingException.class);
  expectedEx.expectMessage("The entity '" + BeanWithNoId.class.getCanonicalName() + "' should have at least one field with javax.persistence.Id/javax.persistence.EmbeddedId annotation");
  parser.parseEntity(entityContext);
}
