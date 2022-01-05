@Test public void testPlaceholdersBindingNonEnumerable(){
  FooBean target=new FooBean();
  DataBinder binder=new DataBinder(target);
  binder.bind(new PropertySourcesPropertyValues(this.propertySources,null,Collections.singleton("foo")));
  assertEquals("bar",target.getFoo());
}
