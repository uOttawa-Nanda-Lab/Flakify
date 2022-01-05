@Test public void testIsInstance() throws Exception {
  assertTrue(ReflectUtils.isInstance(new Foo1(),Foo.class.getName()));
}
