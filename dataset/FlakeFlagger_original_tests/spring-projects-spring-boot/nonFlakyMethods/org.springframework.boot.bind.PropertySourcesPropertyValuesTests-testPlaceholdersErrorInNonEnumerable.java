@Test public void testPlaceholdersErrorInNonEnumerable(){
  TestBean target=new TestBean();
  DataBinder binder=new DataBinder(target);
  this.propertySources.addFirst(new PropertySource<Object>("application","STUFF"){
    @Override public Object getProperty(    String name){
      return new Object();
    }
  }
);
  binder.bind(new PropertySourcesPropertyValues(this.propertySources,null,Collections.singleton("name")));
  assertEquals(null,target.getName());
}
