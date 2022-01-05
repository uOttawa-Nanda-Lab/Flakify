@Test public void testGroup() throws Exception {
  ServiceConfig serviceConfig=new ServiceConfig();
  serviceConfig.setGroup("group");
  assertThat(serviceConfig.getGroup(),equalTo("group"));
}
