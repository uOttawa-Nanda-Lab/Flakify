@Test public void testReliable() throws Exception {
  MethodConfig method=new MethodConfig();
  method.setReliable(true);
  assertThat(method.isReliable(),is(true));
}
