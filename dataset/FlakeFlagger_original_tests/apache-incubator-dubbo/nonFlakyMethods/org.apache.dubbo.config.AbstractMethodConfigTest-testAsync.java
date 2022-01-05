@Test public void testAsync() throws Exception {
  MethodConfig methodConfig=new MethodConfig();
  methodConfig.setAsync(true);
  assertThat(methodConfig.isAsync(),is(true));
}
