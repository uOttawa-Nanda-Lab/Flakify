@Test public void findNonBooleanGetter() throws Exception {
  final Method getter=ReflectionUtils.getGetter(Foo.class,"a");
  assertNotNull(getter);
  assertEquals("getA",getter.getName());
}
