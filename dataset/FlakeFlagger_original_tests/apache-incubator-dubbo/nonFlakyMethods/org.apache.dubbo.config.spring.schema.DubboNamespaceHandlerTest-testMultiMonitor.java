@Test(expected=BeanCreationException.class) public void testMultiMonitor(){
  ClassPathXmlApplicationContext ctx=new ClassPathXmlApplicationContext(ConfigTest.class.getPackage().getName().replace('.','/') + "/multi-monitor.xml");
  ctx.start();
}
