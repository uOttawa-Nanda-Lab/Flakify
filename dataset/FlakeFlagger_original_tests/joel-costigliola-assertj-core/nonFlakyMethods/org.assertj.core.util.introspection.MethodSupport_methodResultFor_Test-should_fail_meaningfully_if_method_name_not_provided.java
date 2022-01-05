@Test public void should_fail_meaningfully_if_method_name_not_provided() throws Exception {
  thrown.expectNullPointerException("Method name can not be empty!");
  MethodSupport.methodResultFor(batman,null);
}
