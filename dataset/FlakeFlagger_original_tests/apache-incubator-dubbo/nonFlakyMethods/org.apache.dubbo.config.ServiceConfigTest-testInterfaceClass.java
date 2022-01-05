@Test public void testInterfaceClass() throws Exception {
  ServiceConfig<Greeting> service=new ServiceConfig<Greeting>();
  service.setInterface(Greeting.class.getName());
  service.setRef(Mockito.mock(Greeting.class));
  assertThat(service.getInterfaceClass() == Greeting.class,is(true));
  service=new ServiceConfig<Greeting>();
  service.setRef(Mockito.mock(Greeting.class,withSettings().extraInterfaces(GenericService.class)));
  assertThat(service.getInterfaceClass() == GenericService.class,is(true));
}
