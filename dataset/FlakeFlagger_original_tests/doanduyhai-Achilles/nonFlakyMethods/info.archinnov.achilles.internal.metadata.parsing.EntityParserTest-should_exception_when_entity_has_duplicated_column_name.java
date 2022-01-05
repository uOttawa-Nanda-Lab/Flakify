@Test public void should_exception_when_entity_has_duplicated_column_name() throws Exception {
  initEntityParsingContext(BeanWithDuplicatedColumnName.class);
  expectedEx.expect(AchillesBeanMappingException.class);
  expectedEx.expectMessage("The property 'name' is already used for the entity '" + BeanWithDuplicatedColumnName.class.getCanonicalName() + "'");
  parser.parseEntity(entityContext);
}
