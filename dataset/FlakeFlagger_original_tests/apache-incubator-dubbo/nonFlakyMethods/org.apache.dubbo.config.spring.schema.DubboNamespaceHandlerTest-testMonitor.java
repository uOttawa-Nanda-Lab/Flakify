@Test public void testMonitor(){
  ClassPathXmlApplicationContext ctx=new ClassPathXmlApplicationContext(ConfigTest.class.getPackage().getName().replace('.','/') + "/provider-with-monitor.xml");
  ctx.start();
  assertThat(ctx.getBean(MonitorConfig.class),not(nullValue()));
}
