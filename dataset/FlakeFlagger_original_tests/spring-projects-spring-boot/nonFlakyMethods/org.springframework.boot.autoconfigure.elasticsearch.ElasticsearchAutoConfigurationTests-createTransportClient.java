@Test public void createTransportClient() throws Exception {
  this.context=new AnnotationConfigApplicationContext();
  EnvironmentTestUtils.addEnvironment(this.context,"spring.data.elasticsearch.cluster-nodes:localhost");
  this.context.register(PropertyPlaceholderAutoConfiguration.class,ElasticsearchAutoConfiguration.class);
  this.thrown.expect(BeanCreationException.class);
  this.thrown.expectMessage("port");
  this.context.refresh();
}
