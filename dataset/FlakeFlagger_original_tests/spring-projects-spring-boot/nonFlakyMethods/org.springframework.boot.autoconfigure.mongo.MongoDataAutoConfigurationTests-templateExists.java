@Test public void templateExists(){
  this.context=new AnnotationConfigApplicationContext(PropertyPlaceholderAutoConfiguration.class,MongoAutoConfiguration.class,MongoDataAutoConfiguration.class);
  assertEquals(1,this.context.getBeanNamesForType(MongoTemplate.class).length);
}
