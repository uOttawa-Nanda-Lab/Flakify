@Test public void testCache() throws Exception {
  MethodConfig methodConfig=new MethodConfig();
  methodConfig.setCache("cache");
  assertThat(methodConfig.getCache(),equalTo("cache"));
}
