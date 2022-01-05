@Test public void testRabbitTemplateEmptyVirtualHost(){
  load(TestConfiguration.class,"spring.rabbitmq.virtual_host:");
  CachingConnectionFactory connectionFactory=this.context.getBean(CachingConnectionFactory.class);
  assertEquals("/",connectionFactory.getVirtualHost());
}
