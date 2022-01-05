@Test public void testBindingAuth(){
  ShellProperties props=new ShellProperties();
  RelaxedDataBinder binder=new RelaxedDataBinder(props,"shell");
  binder.bind(new MutablePropertyValues(Collections.singletonMap("shell.auth","spring")));
  assertFalse(binder.getBindingResult().hasErrors());
  assertEquals("spring",props.getAuth());
}
