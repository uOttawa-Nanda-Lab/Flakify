@Test(expected=ValidationException.class) public void testReturnTypeWithInvalidValidationProvider() throws Exception {
  Validation jValidation=new JValidation();
  URL url=URL.valueOf("test://test:11/org.apache.dubbo.validation.support.jvalidation.JValidation?" + "jvalidation=org.apache.dubbo.validation.Validation");
  jValidation.getValidator(url);
}
