@Test public void testRetries() throws Exception {
  MethodConfig methodConfig=new MethodConfig();
  methodConfig.setRetries(3);
  assertThat(methodConfig.getRetries(),equalTo(3));
}
