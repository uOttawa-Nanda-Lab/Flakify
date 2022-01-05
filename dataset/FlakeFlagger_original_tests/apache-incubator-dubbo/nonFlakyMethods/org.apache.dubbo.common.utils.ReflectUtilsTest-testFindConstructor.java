@Test public void testFindConstructor() throws Exception {
  Constructor constructor=ReflectUtils.findConstructor(Foo3.class,Foo2.class);
  assertNotNull(constructor);
}
