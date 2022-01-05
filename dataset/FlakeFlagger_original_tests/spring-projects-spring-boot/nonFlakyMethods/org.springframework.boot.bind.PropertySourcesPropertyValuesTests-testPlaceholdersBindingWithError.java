@Test public void testPlaceholdersBindingWithError(){
  TestBean target=new TestBean();
  DataBinder binder=new DataBinder(target);
  this.propertySources.addFirst(new MapPropertySource("another",Collections.<String,Object>singletonMap("something","${nonexistent}")));
  binder.bind(new PropertySourcesPropertyValues(this.propertySources));
  assertEquals("bar",target.getName());
}
