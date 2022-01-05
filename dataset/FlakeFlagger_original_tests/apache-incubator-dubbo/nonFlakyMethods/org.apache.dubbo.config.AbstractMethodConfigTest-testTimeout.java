@Test public void testTimeout() throws Exception {
  MethodConfig methodConfig=new MethodConfig();
  methodConfig.setTimeout(10);
  assertThat(methodConfig.getTimeout(),equalTo(10));
}
