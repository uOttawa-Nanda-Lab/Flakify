@Test public void testItWithExistMethod() throws Exception {
  URL url=URL.valueOf("test://test:11/org.apache.dubbo.validation.support.jvalidation.mock.JValidatorTestTarget");
  JValidator jValidator=new JValidator(url);
  jValidator.validate("someMethod1",new Class<?>[]{String.class},new Object[]{"anything"});
}
