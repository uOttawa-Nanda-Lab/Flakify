@Test public void testLoadbalance() throws Exception {
  MethodConfig methodConfig=new MethodConfig();
  methodConfig.setLoadbalance("mockloadbalance");
  assertThat(methodConfig.getLoadbalance(),equalTo("mockloadbalance"));
}
