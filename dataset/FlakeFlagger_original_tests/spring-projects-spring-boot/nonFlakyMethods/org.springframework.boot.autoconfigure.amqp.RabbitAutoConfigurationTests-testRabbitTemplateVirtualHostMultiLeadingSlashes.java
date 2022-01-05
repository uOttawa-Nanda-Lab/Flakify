@Test public void testRabbitTemplateVirtualHostMultiLeadingSlashes(){
  load(TestConfiguration.class,"spring.rabbitmq.virtual_host:///foo");
  CachingConnectionFactory connectionFactory=this.context.getBean(CachingConnectionFactory.class);
  assertEquals("///foo",connectionFactory.getVirtualHost());
}
