@Test public void testGetEmptyObject() throws Exception {
  assertTrue(ReflectUtils.getEmptyObject(Collection.class) instanceof Collection);
  assertTrue(ReflectUtils.getEmptyObject(List.class) instanceof List);
  assertTrue(ReflectUtils.getEmptyObject(Set.class) instanceof Set);
  assertTrue(ReflectUtils.getEmptyObject(Map.class) instanceof Map);
  assertTrue(ReflectUtils.getEmptyObject(Object[].class) instanceof Object[]);
  assertEquals(ReflectUtils.getEmptyObject(String.class),"");
  assertEquals(ReflectUtils.getEmptyObject(short.class),Short.valueOf((short)0));
  assertEquals(ReflectUtils.getEmptyObject(byte.class),Byte.valueOf((byte)0));
  assertEquals(ReflectUtils.getEmptyObject(int.class),Integer.valueOf(0));
  assertEquals(ReflectUtils.getEmptyObject(long.class),Long.valueOf(0));
  assertEquals(ReflectUtils.getEmptyObject(float.class),Float.valueOf(0));
  assertEquals(ReflectUtils.getEmptyObject(double.class),Double.valueOf(0));
  assertEquals(ReflectUtils.getEmptyObject(char.class),Character.valueOf('\0'));
  assertEquals(ReflectUtils.getEmptyObject(boolean.class),Boolean.FALSE);
  EmptyClass object=(EmptyClass)ReflectUtils.getEmptyObject(EmptyClass.class);
  assertNotNull(object);
  assertNotNull(object.getProperty());
}
