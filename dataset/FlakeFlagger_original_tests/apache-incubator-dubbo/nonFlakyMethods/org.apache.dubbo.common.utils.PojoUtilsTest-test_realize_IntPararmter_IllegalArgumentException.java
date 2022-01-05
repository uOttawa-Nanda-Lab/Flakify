@Test public void test_realize_IntPararmter_IllegalArgumentException() throws Exception {
  Method method=PojoUtilsTest.class.getMethod("setInt",int.class);
  assertNotNull(method);
  Object value=PojoUtils.realize("123",method.getParameterTypes()[0],method.getGenericParameterTypes()[0]);
  method.invoke(new PojoUtilsTest(),value);
}
