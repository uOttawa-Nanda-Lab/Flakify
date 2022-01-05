@Test public void should_create_Object_using_reflection() throws Exception {
  Object o=invoker.newInstance("java.lang.Exception",new Class<?>[]{String.class},new Object[]{"Hi"});
  assertTrue(o instanceof Exception);
  Exception e=(Exception)o;
  assertEquals("Hi",e.getMessage());
}
