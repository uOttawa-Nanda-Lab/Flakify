@Test public void testExecutes() throws Exception {
  MethodConfig method=new MethodConfig();
  method.setExecutes(10);
  assertThat(method.getExecutes(),equalTo(10));
}
