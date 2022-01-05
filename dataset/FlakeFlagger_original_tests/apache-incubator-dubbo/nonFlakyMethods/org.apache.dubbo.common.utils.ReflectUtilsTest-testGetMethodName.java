@Test public void testGetMethodName() throws Exception {
  assertThat(ReflectUtils.getName(Foo2.class.getDeclaredMethod("hello",int[].class)),equalTo("java.util.List hello(int[])"));
}
