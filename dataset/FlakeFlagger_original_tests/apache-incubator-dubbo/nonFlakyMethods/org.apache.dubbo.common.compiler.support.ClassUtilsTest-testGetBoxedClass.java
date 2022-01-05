@Test public void testGetBoxedClass(){
  Assert.assertEquals(Boolean.class,ClassUtils.getBoxedClass(boolean.class));
  Assert.assertEquals(Character.class,ClassUtils.getBoxedClass(char.class));
  Assert.assertEquals(Byte.class,ClassUtils.getBoxedClass(byte.class));
  Assert.assertEquals(Short.class,ClassUtils.getBoxedClass(short.class));
  Assert.assertEquals(Integer.class,ClassUtils.getBoxedClass(int.class));
  Assert.assertEquals(Long.class,ClassUtils.getBoxedClass(long.class));
  Assert.assertEquals(Float.class,ClassUtils.getBoxedClass(float.class));
  Assert.assertEquals(Double.class,ClassUtils.getBoxedClass(double.class));
  Assert.assertEquals(ClassUtilsTest.class,ClassUtils.getBoxedClass(ClassUtilsTest.class));
}
