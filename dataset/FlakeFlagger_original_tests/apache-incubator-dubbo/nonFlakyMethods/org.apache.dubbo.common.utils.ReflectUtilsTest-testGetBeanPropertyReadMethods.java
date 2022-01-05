@Test public void testGetBeanPropertyReadMethods() throws Exception {
  Map<String,Method> map=ReflectUtils.getBeanPropertyReadMethods(EmptyClass.class);
  assertThat(map.size(),is(2));
  assertThat(map,hasKey("set"));
  assertThat(map,hasKey("property"));
  for (  Method m : map.values()) {
    if (!m.isAccessible()) {
      fail();
    }
  }
}
