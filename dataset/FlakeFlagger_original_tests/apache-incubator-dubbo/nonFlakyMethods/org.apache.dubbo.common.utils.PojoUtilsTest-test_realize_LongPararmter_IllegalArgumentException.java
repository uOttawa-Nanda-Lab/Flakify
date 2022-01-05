@Test public void test_realize_LongPararmter_IllegalArgumentException() throws Exception {
  Method method=PojoUtilsTest.class.getMethod("setLong",long.class);
  assertNotNull(method);
  Object value=PojoUtils.realize("563439743927993",method.getParameterTypes()[0],method.getGenericParameterTypes()[0]);
  method.invoke(new PojoUtilsTest(),value);
}
