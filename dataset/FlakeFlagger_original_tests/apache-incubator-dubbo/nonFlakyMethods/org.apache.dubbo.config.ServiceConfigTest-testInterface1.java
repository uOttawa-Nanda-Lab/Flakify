@Test(expected=IllegalStateException.class) public void testInterface1() throws Exception {
  ServiceConfig<DemoService> service=new ServiceConfig<DemoService>();
  service.setInterface(DemoServiceImpl.class);
}
