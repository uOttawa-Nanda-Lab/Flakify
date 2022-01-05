@Test public void testInterface2() throws Exception {
  ServiceConfig<DemoService> service=new ServiceConfig<DemoService>();
  service.setInterface(DemoService.class);
  assertThat(service.getInterface(),equalTo(DemoService.class.getName()));
}
