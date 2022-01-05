@Test public void testGetService(){
  TestService service=mock(TestService.class);
  ServiceBean serviceBean=new ServiceBean(service);
  Service beanService=serviceBean.getService();
  Assert.assertThat(beanService,not(nullValue()));
}
