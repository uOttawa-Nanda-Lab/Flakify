@Test public void testReturn() throws Exception {
  MethodConfig method=new MethodConfig();
  method.setReturn(true);
  assertThat(method.isReturn(),is(true));
}
