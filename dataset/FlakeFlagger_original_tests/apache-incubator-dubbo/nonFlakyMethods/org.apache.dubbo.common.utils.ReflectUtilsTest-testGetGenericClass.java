@Test public void testGetGenericClass() throws Exception {
  assertThat(ReflectUtils.getGenericClass(Foo1.class),sameInstance(String.class));
}
