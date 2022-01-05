@Test public void testInvokeToString() throws Throwable {
  String methodName="toString";
  when(invoker.toString()).thenReturn(methodName);
  Method method=invoker.getClass().getMethod(methodName);
  Object result=invokerInvocationHandler.invoke(null,method,new Object[]{});
  Assert.assertEquals(methodName,result);
}
