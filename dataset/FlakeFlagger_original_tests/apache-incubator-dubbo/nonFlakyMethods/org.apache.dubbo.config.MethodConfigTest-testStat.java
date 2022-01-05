@Test public void testStat() throws Exception {
  MethodConfig method=new MethodConfig();
  method.setStat(10);
  assertThat(method.getStat(),equalTo(10));
}
