@Test public void should_build_meta_data_without_bean_validation() throws Exception {
  List<Class<?>> entities=Arrays.<Class<?>>asList(UserBean.class);
  when(parser.parseEntity(any(EntityParsingContext.class))).thenReturn(meta);
  when(configContext.isClassConstrained(UserBean.class)).thenReturn(false);
  ParsingResult parsingResult=bootstrapper.buildMetaDatas(configContext,entities);
  assertThat(parsingResult.getMetaMap().get(UserBean.class)).isSameAs(meta);
  assertThat(parsingResult.hasSimpleCounter()).isFalse();
  verify(configContext,never()).addBeanValidationInterceptor(meta);
}
