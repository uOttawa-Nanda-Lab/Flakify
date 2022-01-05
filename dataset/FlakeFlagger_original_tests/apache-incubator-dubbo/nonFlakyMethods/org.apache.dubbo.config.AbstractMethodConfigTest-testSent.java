@Test public void testSent() throws Exception {
  MethodConfig methodConfig=new MethodConfig();
  methodConfig.setSent(true);
  assertThat(methodConfig.getSent(),is(true));
}
