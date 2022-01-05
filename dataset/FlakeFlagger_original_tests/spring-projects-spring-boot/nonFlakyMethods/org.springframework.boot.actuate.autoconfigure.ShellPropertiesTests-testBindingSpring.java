@Test public void testBindingSpring(){
  SpringAuthenticationProperties props=new SpringAuthenticationProperties();
  RelaxedDataBinder binder=new RelaxedDataBinder(props,"shell.auth.spring");
  binder.bind(new MutablePropertyValues(Collections.singletonMap("shell.auth.spring.roles","role1, role2")));
  assertFalse(binder.getBindingResult().hasErrors());
  Properties p=new Properties();
  props.applyToCrshShellConfig(p);
  assertEquals("role1, role2",p.get("crash.auth.spring.roles"));
}
