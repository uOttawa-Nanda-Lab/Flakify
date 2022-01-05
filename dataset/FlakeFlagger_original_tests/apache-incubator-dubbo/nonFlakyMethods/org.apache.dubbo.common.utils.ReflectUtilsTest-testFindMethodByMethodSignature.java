@Test public void testFindMethodByMethodSignature() throws Exception {
  Method m=ReflectUtils.findMethodByMethodSignature(TestedClass.class,"method1",null);
  assertEquals("method1",m.getName());
  Class<?>[] parameterTypes=m.getParameterTypes();
  assertEquals(1,parameterTypes.length);
  assertEquals(int.class,parameterTypes[0]);
}
