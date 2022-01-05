@Test public void clientExists(){
  this.context=new AnnotationConfigApplicationContext(PropertyPlaceholderAutoConfiguration.class,MongoAutoConfiguration.class);
  assertEquals(1,this.context.getBeanNamesForType(Mongo.class).length);
}
