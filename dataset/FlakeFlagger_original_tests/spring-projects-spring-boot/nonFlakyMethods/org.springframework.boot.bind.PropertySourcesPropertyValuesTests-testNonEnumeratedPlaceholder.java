@Test public void testNonEnumeratedPlaceholder(){
  this.propertySources.addFirst(new PropertySource<String>("another","baz"){
    @Override public Object getProperty(    String name){
      if (name.equals(getSource())) {
        return "${foo}";
      }
      return null;
    }
  }
);
  PropertySourcesPropertyValues propertyValues=new PropertySourcesPropertyValues(this.propertySources,null,Collections.singleton("baz"));
  assertEquals("bar",propertyValues.getPropertyValue("baz").getValue());
}
