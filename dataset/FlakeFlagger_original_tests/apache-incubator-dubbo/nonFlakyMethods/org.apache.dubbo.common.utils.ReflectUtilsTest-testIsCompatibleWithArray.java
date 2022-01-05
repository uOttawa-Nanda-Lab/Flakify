@Test public void testIsCompatibleWithArray() throws Exception {
  assertFalse(ReflectUtils.isCompatible(new Class[]{short.class,int.class},new Object[]{(short)1}));
  assertFalse(ReflectUtils.isCompatible(new Class[]{double.class},new Object[]{"hello"}));
  assertTrue(ReflectUtils.isCompatible(new Class[]{double.class},new Object[]{1.2}));
}
