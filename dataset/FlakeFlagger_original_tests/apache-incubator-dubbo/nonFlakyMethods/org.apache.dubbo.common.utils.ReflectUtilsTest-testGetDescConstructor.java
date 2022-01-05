@Test public void testGetDescConstructor() throws Exception {
  assertThat(ReflectUtils.getDesc(Foo2.class.getConstructors()[0]),equalTo("(Ljava/util/List;[I)V"));
}
