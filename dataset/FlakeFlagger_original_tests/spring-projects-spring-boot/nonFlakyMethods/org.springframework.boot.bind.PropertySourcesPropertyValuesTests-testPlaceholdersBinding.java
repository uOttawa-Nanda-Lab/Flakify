@Test public void testPlaceholdersBinding(){
  TestBean target=new TestBean();
  DataBinder binder=new DataBinder(target);
  binder.bind(new PropertySourcesPropertyValues(this.propertySources));
  assertEquals("bar",target.getName());
}
