@Test public void should_return_null_when_bean_validation_disabled() throws Exception {
  ConfigMap params=new ConfigMap();
  params.put(BEAN_VALIDATION_ENABLE,false);
  assertThat(extractor.initValidator(params)).isNull();
}
