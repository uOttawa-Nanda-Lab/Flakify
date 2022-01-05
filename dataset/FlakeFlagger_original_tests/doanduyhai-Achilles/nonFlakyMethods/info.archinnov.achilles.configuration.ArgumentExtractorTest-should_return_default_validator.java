@Test public void should_return_default_validator() throws Exception {
  ConfigMap params=new ConfigMap();
  params.put(BEAN_VALIDATION_ENABLE,true);
  Validator defaultValidator=extractor.initValidator(params);
  assertThat(defaultValidator).isNotNull().isInstanceOf(ValidatorImpl.class);
}
