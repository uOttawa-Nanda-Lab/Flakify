@Test public void should_fail_meaningfully_if_method_name_is_empty() throws Exception {
  thrown.expectIllegalArgumentException("Method name can not be empty!");
  MethodSupport.methodResultFor(batman,"");
}
