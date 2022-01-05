@Test public void should_detect_constrained_class() throws Exception {
  ConfigurationContext context=new ConfigurationContext();
  context.setBeanValidator(validator);
  when(validator.getConstraintsForClass(BeanWithFieldLevelConstraint.class).isBeanConstrained()).thenReturn(true);
  assertThat(context.isClassConstrained(BeanWithFieldLevelConstraint.class)).isTrue();
}
