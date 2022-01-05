@Test public void containerWithMultipartConfigDisabled(){
  this.context=new AnnotationConfigEmbeddedWebApplicationContext();
  this.context.getEnvironment().getPropertySources().addFirst(new PropertySource<Object>("test"){
    @Override public Object getProperty(    String name){
      if (name.toLowerCase().contains("multipart.enabled")) {
        return "false";
      }
      return null;
    }
  }
);
  this.context.register(ContainerWithNoMultipartTomcat.class,BaseConfiguration.class);
  this.context.refresh();
  assertEquals(0,this.context.getBeansOfType(MultipartConfigElement.class).size());
}
