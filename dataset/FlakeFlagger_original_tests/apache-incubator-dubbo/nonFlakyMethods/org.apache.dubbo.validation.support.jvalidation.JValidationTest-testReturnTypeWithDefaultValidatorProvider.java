@Test public void testReturnTypeWithDefaultValidatorProvider() throws Exception {
  Validation jValidation=new JValidation();
  URL url=URL.valueOf("test://test:11/org.apache.dubbo.validation.support.jvalidation.JValidation");
  Validator validator=jValidation.getValidator(url);
  assertThat(validator instanceof JValidator,is(true));
}
