@Test(expected=BeanCreationException.class) public void nonExistentTemplateLocation(){
  registerAndRefreshContext("spring.freemarker.templateLoaderPath:" + "classpath:/does-not-exist/,classpath:/also-does-not-exist");
}
