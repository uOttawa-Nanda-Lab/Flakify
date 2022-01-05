@Test public void gridFsTemplateExists(){
  this.context=new AnnotationConfigApplicationContext();
  EnvironmentTestUtils.addEnvironment(this.context,"spring.data.mongodb.gridFsDatabase:grid");
  this.context.register(PropertyPlaceholderAutoConfiguration.class,MongoAutoConfiguration.class,MongoDataAutoConfiguration.class);
  this.context.refresh();
  assertEquals(1,this.context.getBeanNamesForType(GridFsTemplate.class).length);
}
