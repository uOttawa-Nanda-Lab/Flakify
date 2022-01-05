@Test public void canBindCharArrayPassword(){
  AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext();
  EnvironmentTestUtils.addEnvironment(context,"spring.data.mongodb.password:word");
  context.register(Conf.class);
  context.refresh();
  MongoProperties properties=context.getBean(MongoProperties.class);
  assertThat(properties.getPassword(),equalTo("word".toCharArray()));
}
