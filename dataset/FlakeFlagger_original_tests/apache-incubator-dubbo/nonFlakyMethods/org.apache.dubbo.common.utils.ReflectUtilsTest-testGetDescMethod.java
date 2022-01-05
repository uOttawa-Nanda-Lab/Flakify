@Test public void testGetDescMethod() throws Exception {
  assertThat(ReflectUtils.getDesc(Foo2.class.getDeclaredMethod("hello",int[].class)),equalTo("hello([I)Ljava/util/List;"));
}
