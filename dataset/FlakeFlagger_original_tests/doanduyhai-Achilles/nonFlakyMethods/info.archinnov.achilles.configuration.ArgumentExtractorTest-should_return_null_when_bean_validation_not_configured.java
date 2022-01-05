@Test public void should_return_null_when_bean_validation_not_configured() throws Exception {
  ConfigMap params=new ConfigMap();
  assertThat(extractor.initValidator(params)).isNull();
}
