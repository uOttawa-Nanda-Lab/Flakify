@Test public void testGetBeanPropertyFields() throws Exception {
  Map<String,Field> map=ReflectUtils.getBeanPropertyFields(EmptyClass.class);
  assertThat(map.size(),is(2));
  assertThat(map,hasKey("set"));
  assertThat(map,hasKey("property"));
  for (  Field f : map.values()) {
    if (!f.isAccessible()) {
      fail();
    }
  }
}
