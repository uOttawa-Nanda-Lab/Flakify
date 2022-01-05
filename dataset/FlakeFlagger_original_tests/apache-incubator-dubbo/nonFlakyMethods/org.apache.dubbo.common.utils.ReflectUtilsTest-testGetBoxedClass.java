@Test public void testGetBoxedClass() throws Exception {
  assertThat(ReflectUtils.getBoxedClass(int.class),sameInstance(Integer.class));
  assertThat(ReflectUtils.getBoxedClass(boolean.class),sameInstance(Boolean.class));
  assertThat(ReflectUtils.getBoxedClass(long.class),sameInstance(Long.class));
  assertThat(ReflectUtils.getBoxedClass(float.class),sameInstance(Float.class));
  assertThat(ReflectUtils.getBoxedClass(double.class),sameInstance(Double.class));
  assertThat(ReflectUtils.getBoxedClass(char.class),sameInstance(Character.class));
  assertThat(ReflectUtils.getBoxedClass(byte.class),sameInstance(Byte.class));
  assertThat(ReflectUtils.getBoxedClass(short.class),sameInstance(Short.class));
}
