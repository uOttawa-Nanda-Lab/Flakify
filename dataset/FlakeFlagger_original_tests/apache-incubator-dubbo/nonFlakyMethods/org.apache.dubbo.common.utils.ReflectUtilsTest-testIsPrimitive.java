@Test public void testIsPrimitive() throws Exception {
  assertTrue(ReflectUtils.isPrimitive(boolean.class));
  assertTrue(ReflectUtils.isPrimitive(String.class));
  assertTrue(ReflectUtils.isPrimitive(Boolean.class));
  assertTrue(ReflectUtils.isPrimitive(Character.class));
  assertTrue(ReflectUtils.isPrimitive(Number.class));
  assertTrue(ReflectUtils.isPrimitive(Date.class));
  assertFalse(ReflectUtils.isPrimitive(Map.class));
}
