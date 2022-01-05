@Test public void testIsBeanPropertyReadMethod() throws Exception {
  Method method=EmptyClass.class.getMethod("getProperty");
  assertTrue(ReflectUtils.isBeanPropertyReadMethod(method));
  method=EmptyClass.class.getMethod("getProperties");
  assertFalse(ReflectUtils.isBeanPropertyReadMethod(method));
  method=EmptyClass.class.getMethod("isProperty");
  assertFalse(ReflectUtils.isBeanPropertyReadMethod(method));
  method=EmptyClass.class.getMethod("getPropertyIndex",int.class);
  assertFalse(ReflectUtils.isBeanPropertyReadMethod(method));
}
