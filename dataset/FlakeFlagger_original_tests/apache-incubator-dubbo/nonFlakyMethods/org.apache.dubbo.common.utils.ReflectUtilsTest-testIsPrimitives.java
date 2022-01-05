@Test public void testIsPrimitives() throws Exception {
  assertTrue(ReflectUtils.isPrimitives(boolean[].class));
  assertTrue(ReflectUtils.isPrimitives(byte.class));
  assertFalse(ReflectUtils.isPrimitive(Map[].class));
}
