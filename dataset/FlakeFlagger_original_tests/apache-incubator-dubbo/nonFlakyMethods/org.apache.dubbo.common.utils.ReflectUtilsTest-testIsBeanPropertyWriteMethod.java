@Test public void testIsBeanPropertyWriteMethod() throws Exception {
  Method method=EmptyClass.class.getMethod("setProperty",EmptyProperty.class);
  assertTrue(ReflectUtils.isBeanPropertyWriteMethod(method));
  method=EmptyClass.class.getMethod("setSet",boolean.class);
  assertTrue(ReflectUtils.isBeanPropertyWriteMethod(method));
}
