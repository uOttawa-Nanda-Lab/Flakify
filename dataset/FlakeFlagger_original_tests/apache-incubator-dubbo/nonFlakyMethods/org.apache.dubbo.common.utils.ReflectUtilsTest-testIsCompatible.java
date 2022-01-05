@Test public void testIsCompatible() throws Exception {
  assertTrue(ReflectUtils.isCompatible(short.class,(short)1));
  assertTrue(ReflectUtils.isCompatible(int.class,1));
  assertTrue(ReflectUtils.isCompatible(double.class,1.2));
  assertTrue(ReflectUtils.isCompatible(Object.class,1.2));
  assertTrue(ReflectUtils.isCompatible(List.class,new ArrayList<String>()));
}
