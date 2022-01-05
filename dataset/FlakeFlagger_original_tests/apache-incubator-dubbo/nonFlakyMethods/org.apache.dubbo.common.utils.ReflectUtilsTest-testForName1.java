@Test public void testForName1() throws Exception {
  assertThat(ReflectUtils.forName(ReflectUtils.class.getName()),sameInstance(ReflectUtils.class));
}
