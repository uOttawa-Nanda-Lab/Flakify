@Test public void testRetry() throws Exception {
  MethodConfig method=new MethodConfig();
  method.setRetry(true);
  assertThat(method.isRetry(),is(true));
}
