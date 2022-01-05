@Test public void testFindMethodByMethodSignature_override() throws Exception {
{
    Method m=ReflectUtils.findMethodByMethodSignature(TestedClass.class,"overrideMethod",new String[]{"int"});
    assertEquals("overrideMethod",m.getName());
    Class<?>[] parameterTypes=m.getParameterTypes();
    assertEquals(1,parameterTypes.length);
    assertEquals(int.class,parameterTypes[0]);
  }
{
    Method m=ReflectUtils.findMethodByMethodSignature(TestedClass.class,"overrideMethod",new String[]{"java.lang.Integer"});
    assertEquals("overrideMethod",m.getName());
    Class<?>[] parameterTypes=m.getParameterTypes();
    assertEquals(1,parameterTypes.length);
    assertEquals(Integer.class,parameterTypes[0]);
  }
}
