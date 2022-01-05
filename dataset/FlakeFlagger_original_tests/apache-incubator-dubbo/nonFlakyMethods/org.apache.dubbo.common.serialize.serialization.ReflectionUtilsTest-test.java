@Test public void test(){
  assertTrue(ReflectionUtils.checkZeroArgConstructor(String.class));
  assertTrue(ReflectionUtils.checkZeroArgConstructor(Bar.class));
  assertFalse(ReflectionUtils.checkZeroArgConstructor(Foo.class));
}
