@Test public void testGetPropertyNameFromBeanWriteMethod() throws Exception {
  Method method=EmptyClass.class.getMethod("setProperty",EmptyProperty.class);
  assertEquals(ReflectUtils.getPropertyNameFromBeanWriteMethod(method),"property");
}
