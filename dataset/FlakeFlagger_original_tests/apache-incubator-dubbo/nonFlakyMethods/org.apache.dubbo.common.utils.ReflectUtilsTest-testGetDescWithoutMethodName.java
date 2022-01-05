@Test public void testGetDescWithoutMethodName() throws Exception {
  assertThat(ReflectUtils.getDescWithoutMethodName(Foo2.class.getDeclaredMethod("hello",int[].class)),equalTo("([I)Ljava/util/List;"));
}
