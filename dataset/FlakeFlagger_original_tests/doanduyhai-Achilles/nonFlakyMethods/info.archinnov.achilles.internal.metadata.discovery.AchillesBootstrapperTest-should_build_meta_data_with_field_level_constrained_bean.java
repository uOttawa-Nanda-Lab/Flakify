@Test public void should_build_meta_data_with_field_level_constrained_bean() throws Exception {
  List<Class<?>> entities=Arrays.<Class<?>>asList(BeanWithFieldLevelConstraint.class);
  when(parser.parseEntity(any(EntityParsingContext.class))).thenReturn(meta);
  when(configContext.isClassConstrained(BeanWithFieldLevelConstraint.class)).thenReturn(true);
  ParsingResult parsingResult=bootstrapper.buildMetaDatas(configContext,entities);
  assertThat(parsingResult.getMetaMap().get(BeanWithFieldLevelConstraint.class)).isSameAs(meta);
  verify(configContext).addBeanValidationInterceptor(meta);
}
