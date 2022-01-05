@Test(expected=BeanCreationException.class) public void testMultiProviderConfig(){
  ClassPathXmlApplicationContext ctx=new ClassPathXmlApplicationContext(ConfigTest.class.getPackage().getName().replace('.','/') + "/provider-multi.xml");
  ctx.start();
}
