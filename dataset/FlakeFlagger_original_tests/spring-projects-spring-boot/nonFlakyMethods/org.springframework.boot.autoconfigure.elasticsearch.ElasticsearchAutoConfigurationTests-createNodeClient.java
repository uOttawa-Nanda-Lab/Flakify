@Test public void createNodeClient(){
  this.context=new AnnotationConfigApplicationContext(PropertyPlaceholderAutoConfiguration.class,ElasticsearchAutoConfiguration.class);
  assertEquals(1,this.context.getBeanNamesForType(Client.class).length);
  assertThat(this.context.getBean(Client.class),instanceOf(NodeClient.class));
}
