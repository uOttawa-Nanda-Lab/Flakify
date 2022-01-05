@Test public void testGetOptionalBean(){
  applicationContext.register(TestBean.class);
  applicationContext.refresh();
  TestBean testBean=BeanFactoryUtils.getOptionalBean(applicationContext,"testBean",TestBean.class);
  Assert.assertNotNull(testBean);
  Assert.assertEquals("Hello,World",testBean.getName());
}
