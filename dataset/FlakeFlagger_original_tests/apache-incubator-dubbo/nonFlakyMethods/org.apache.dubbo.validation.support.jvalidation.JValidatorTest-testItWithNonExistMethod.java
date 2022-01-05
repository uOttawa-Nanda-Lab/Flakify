@Test(expected=NoSuchMethodException.class) public void testItWithNonExistMethod() throws Exception {
  URL url=URL.valueOf("test://test:11/org.apache.dubbo.validation.support.jvalidation.mock.JValidatorTestTarget");
  JValidator jValidator=new JValidator(url);
  jValidator.validate("nonExistingMethod",new Class<?>[]{String.class},new Object[]{"arg1"});
}
