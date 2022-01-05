@Test(expected=ConstraintViolationException.class) public void testItWhenItViolatedConstraint() throws Exception {
  URL url=URL.valueOf("test://test:11/org.apache.dubbo.validation.support.jvalidation.mock.JValidatorTestTarget");
  JValidator jValidator=new JValidator(url);
  jValidator.validate("someMethod2",new Class<?>[]{ValidationParameter.class},new Object[]{new ValidationParameter()});
}
