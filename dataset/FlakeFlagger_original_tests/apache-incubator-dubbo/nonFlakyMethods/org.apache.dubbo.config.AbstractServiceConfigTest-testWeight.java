@Test public void testWeight() throws Exception {
  ServiceConfig serviceConfig=new ServiceConfig();
  serviceConfig.setWeight(500);
  assertThat(serviceConfig.getWeight(),equalTo(500));
}
