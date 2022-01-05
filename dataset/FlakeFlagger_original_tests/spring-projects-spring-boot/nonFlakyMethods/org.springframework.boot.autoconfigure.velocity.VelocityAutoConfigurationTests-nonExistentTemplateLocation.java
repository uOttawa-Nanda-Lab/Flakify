@Test(expected=BeanCreationException.class) public void nonExistentTemplateLocation(){
  registerAndRefreshContext("spring.velocity.resourceLoaderPath:" + "classpath:/does-not-exist/");
}
