@Test public void decorateWithServiceLoader(){
  Module module=Module.forClass(TestService.class);
  ServiceLoader<TestService> loader=module.loadService(TestService.class);
  String s="Hello";
  for (  TestService service : loader) {
    s=service.decorate(s);
  }
  Assert.assertEquals("#TestService#Hello",s);
}
