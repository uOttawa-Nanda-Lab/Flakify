@Test public void testUniqueServiceName() throws Exception {
  ServiceConfig<Greeting> service=new ServiceConfig<Greeting>();
  service.setGroup("dubbo");
  service.setInterface(Greeting.class);
  service.setVersion("1.0.0");
  assertThat(service.getUniqueServiceName(),equalTo("dubbo/" + Greeting.class.getName() + ":1.0.0"));
}
