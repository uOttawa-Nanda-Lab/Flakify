@Test(expected=BeanCreationException.class) public void testNotificationWithWrongBean(){
  ClassPathXmlApplicationContext ctx=new ClassPathXmlApplicationContext(ConfigTest.class.getPackage().getName().replace('.','/') + "/consumer-notification.xml");
  ctx.start();
}
