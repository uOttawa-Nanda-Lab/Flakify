@Test public void testGetPropertyNameFromBeanReadMethod() throws Exception {
  Method method=EmptyClass.class.getMethod("getProperty");
  assertEquals(ReflectUtils.getPropertyNameFromBeanReadMethod(method),"property");
  method=EmptyClass.class.getMethod("isSet");
  assertEquals(ReflectUtils.getPropertyNameFromBeanReadMethod(method),"set");
}
