@Test public void should_fail_meaningfully_if_object_instance_not_provided() throws Exception {
  thrown.expectNullPointerException("Object instance can not be null!");
  MethodSupport.methodResultFor(null,"methodName");
}
