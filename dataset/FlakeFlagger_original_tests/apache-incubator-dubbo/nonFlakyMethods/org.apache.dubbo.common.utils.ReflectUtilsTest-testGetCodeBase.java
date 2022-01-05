@Test public void testGetCodeBase() throws Exception {
  assertNull(ReflectUtils.getCodeBase(null));
  assertNull(ReflectUtils.getCodeBase(String.class));
  assertNotNull(ReflectUtils.getCodeBase(ReflectUtils.class));
}
