@Test public void templateExists(){
  this.context=new AnnotationConfigApplicationContext(PropertyPlaceholderAutoConfiguration.class,ElasticsearchAutoConfiguration.class,ElasticsearchDataAutoConfiguration.class);
  assertEquals(1,this.context.getBeanNamesForType(ElasticsearchTemplate.class).length);
}
