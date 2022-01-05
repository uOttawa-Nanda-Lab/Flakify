@SuppressWarnings("deprecation") @Test public void optionsAddedButNoHost(){
  this.context=new AnnotationConfigApplicationContext();
  EnvironmentTestUtils.addEnvironment(this.context,"spring.data.mongodb.uri:mongodb://localhost/test");
  this.context.register(OptionsConfig.class,PropertyPlaceholderAutoConfiguration.class,MongoAutoConfiguration.class);
  this.context.refresh();
  assertEquals(300,this.context.getBean(Mongo.class).getMongoOptions().getSocketTimeout());
}
