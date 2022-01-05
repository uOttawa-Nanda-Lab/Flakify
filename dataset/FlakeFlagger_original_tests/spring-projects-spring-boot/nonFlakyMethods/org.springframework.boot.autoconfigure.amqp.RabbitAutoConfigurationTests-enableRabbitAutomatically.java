@Test public void enableRabbitAutomatically() throws Exception {
  load(NoEnableRabbitConfiguration.class);
  AnnotationConfigApplicationContext ctx=this.context;
  ctx.getBean(RabbitListenerConfigUtils.RABBIT_LISTENER_ANNOTATION_PROCESSOR_BEAN_NAME);
  ctx.getBean(RabbitListenerConfigUtils.RABBIT_LISTENER_ENDPOINT_REGISTRY_BEAN_NAME);
}
