@Test public void testSerialization() throws Exception {
  ServiceConfig serviceConfig=new ServiceConfig();
  serviceConfig.setSerialization("serialization");
  assertThat(serviceConfig.getSerialization(),equalTo("serialization"));
}
