@Test public void testContainer(){
  SpringContainer container=(SpringContainer)ExtensionLoader.getExtensionLoader(Container.class).getExtension("spring");
  container.start();
  Assert.assertEquals(SpringContainer.class,container.context.getBean("container").getClass());
  container.stop();
}
