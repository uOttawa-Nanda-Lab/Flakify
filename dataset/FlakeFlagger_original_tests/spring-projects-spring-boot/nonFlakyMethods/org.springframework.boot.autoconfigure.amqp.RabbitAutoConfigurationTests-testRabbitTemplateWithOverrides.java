@Test public void testRabbitTemplateWithOverrides(){
  load(TestConfiguration.class,"spring.rabbitmq.host:remote-server","spring.rabbitmq.port:9000","spring.rabbitmq.username:alice","spring.rabbitmq.password:secret","spring.rabbitmq.virtual_host:/vhost");
  CachingConnectionFactory connectionFactory=this.context.getBean(CachingConnectionFactory.class);
  assertEquals("remote-server",connectionFactory.getHost());
  assertEquals(9000,connectionFactory.getPort());
  assertEquals("/vhost",connectionFactory.getVirtualHost());
}
