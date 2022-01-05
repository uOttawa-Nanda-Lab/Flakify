@Test public void should_get_provided_custom_validator() throws Exception {
  FakeValidator customValidator=new FakeValidator();
  ConfigMap params=new ConfigMap();
  params.put(BEAN_VALIDATION_ENABLE,true);
  params.put(BEAN_VALIDATION_VALIDATOR,customValidator);
  Validator validator=extractor.initValidator(params);
  assertThat(validator).isNotNull();
  assertThat(validator).isSameAs(customValidator);
}
