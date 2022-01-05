@Test public void should_not_detect_constrained_class_if_bean_validation_disabled() throws Exception {
  ConfigurationContext context=new ConfigurationContext();
  assertThat(context.isClassConstrained(BeanWithFieldLevelConstraint.class)).isFalse();
}
