@Test public void testSticky() throws Exception {
  MethodConfig method=new MethodConfig();
  method.setSticky(true);
  assertThat(method.getSticky(),is(true));
}
