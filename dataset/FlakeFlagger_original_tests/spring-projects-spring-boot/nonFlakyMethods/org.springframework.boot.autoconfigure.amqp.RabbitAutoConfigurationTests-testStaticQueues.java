@Test public void testStaticQueues(){
  load(TestConfiguration.class,"spring.rabbitmq.dynamic:false");
  this.thrown.expect(NoSuchBeanDefinitionException.class);
  this.thrown.expectMessage("No qualifying bean of type " + "[org.springframework.amqp.core.AmqpAdmin] is defined");
  this.context.getBean(AmqpAdmin.class);
}
