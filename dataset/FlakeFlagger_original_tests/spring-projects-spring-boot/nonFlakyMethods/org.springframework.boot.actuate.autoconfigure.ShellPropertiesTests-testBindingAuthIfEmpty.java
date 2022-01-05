@Test public void testBindingAuthIfEmpty(){
  ShellProperties props=new ShellProperties();
  RelaxedDataBinder binder=new RelaxedDataBinder(props,"shell");
  binder.bind(new MutablePropertyValues(Collections.singletonMap("shell.auth","")));
  assertTrue(binder.getBindingResult().hasErrors());
  assertEquals("simple",props.getAuth());
}
