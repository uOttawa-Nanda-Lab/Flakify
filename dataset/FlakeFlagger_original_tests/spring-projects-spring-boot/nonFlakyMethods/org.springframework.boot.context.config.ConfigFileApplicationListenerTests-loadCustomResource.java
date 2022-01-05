@Test public void loadCustomResource() throws Exception {
  this.event.getSpringApplication().setResourceLoader(new ResourceLoader(){
    @Override public Resource getResource(    final String location){
      if (location.equals("classpath:/custom.properties")) {
        return new ByteArrayResource("the.property: fromcustom".getBytes(),location){
          @Override public String getFilename(){
            return location;
          }
        }
;
      }
      return null;
    }
    @Override public ClassLoader getClassLoader(){
      return getClass().getClassLoader();
    }
  }
);
  this.initializer.setSearchNames("custom");
  this.initializer.onApplicationEvent(this.event);
  String property=this.environment.getProperty("the.property");
  assertThat(property,equalTo("fromcustom"));
}
