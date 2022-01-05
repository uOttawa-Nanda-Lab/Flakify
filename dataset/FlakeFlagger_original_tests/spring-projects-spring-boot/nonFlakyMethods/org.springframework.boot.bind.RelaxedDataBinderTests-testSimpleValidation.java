@Test public void testSimpleValidation() throws Exception {
  ValidatedTarget target=new ValidatedTarget();
  BindingResult result=bind(target,"");
  assertEquals(1,result.getErrorCount());
}
