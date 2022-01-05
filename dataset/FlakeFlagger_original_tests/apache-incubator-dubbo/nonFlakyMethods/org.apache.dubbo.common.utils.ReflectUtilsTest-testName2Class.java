@Test public void testName2Class() throws Exception {
  assertEquals(boolean.class,ReflectUtils.name2class("boolean"));
  assertEquals(boolean[].class,ReflectUtils.name2class("boolean[]"));
  assertEquals(int[][].class,ReflectUtils.name2class(ReflectUtils.getName(int[][].class)));
  assertEquals(ReflectUtilsTest[].class,ReflectUtils.name2class(ReflectUtils.getName(ReflectUtilsTest[].class)));
}
