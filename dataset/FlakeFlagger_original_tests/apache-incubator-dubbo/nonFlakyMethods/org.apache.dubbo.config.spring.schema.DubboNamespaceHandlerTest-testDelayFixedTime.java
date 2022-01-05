@Test public void testDelayFixedTime(){
  ClassPathXmlApplicationContext ctx=new ClassPathXmlApplicationContext(ConfigTest.class.getPackage().getName().replace('.','/') + "/delay-fixed-time.xml");
  ctx.start();
  assertThat(ctx.getBean(ServiceBean.class).getDelay(),is(300));
}
