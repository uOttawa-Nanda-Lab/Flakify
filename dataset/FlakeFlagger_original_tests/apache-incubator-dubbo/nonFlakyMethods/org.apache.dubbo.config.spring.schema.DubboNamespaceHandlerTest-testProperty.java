@Test public void testProperty(){
  ClassPathXmlApplicationContext ctx=new ClassPathXmlApplicationContext(ConfigTest.class.getPackage().getName().replace('.','/') + "/service-class.xml");
  ctx.start();
  ServiceBean serviceBean=ctx.getBean(ServiceBean.class);
  String prefix=((DemoServiceImpl)serviceBean.getRef()).getPrefix();
  assertThat(prefix,is("welcome:"));
}
