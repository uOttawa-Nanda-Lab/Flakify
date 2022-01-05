@Test public void findBooleanGetter() throws Exception {
  final Method getter=ReflectionUtils.getGetter(Foo.class,"b");
  assertNotNull(getter);
  assertEquals("isB",getter.getName());
}
