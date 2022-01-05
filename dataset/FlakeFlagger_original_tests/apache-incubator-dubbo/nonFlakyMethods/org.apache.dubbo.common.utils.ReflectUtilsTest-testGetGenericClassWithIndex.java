@Test public void testGetGenericClassWithIndex() throws Exception {
  assertThat(ReflectUtils.getGenericClass(Foo1.class,0),sameInstance(String.class));
  assertThat(ReflectUtils.getGenericClass(Foo1.class,1),sameInstance(Integer.class));
  assertThat(ReflectUtils.getGenericClass(Foo2.class,0),sameInstance(List.class));
  assertThat(ReflectUtils.getGenericClass(Foo2.class,1),sameInstance(int.class));
  assertThat(ReflectUtils.getGenericClass(Foo3.class,0),sameInstance(Foo1.class));
  assertThat(ReflectUtils.getGenericClass(Foo3.class,1),sameInstance(Foo2.class));
}
