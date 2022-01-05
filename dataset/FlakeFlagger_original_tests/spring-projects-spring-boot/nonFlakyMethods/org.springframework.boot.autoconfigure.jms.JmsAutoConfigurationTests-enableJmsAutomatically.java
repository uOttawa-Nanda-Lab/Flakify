@Test public void enableJmsAutomatically() throws Exception {
  load(NoEnableJmsConfiguration.class);
  AnnotationConfigApplicationContext ctx=this.context;
  ctx.getBean(JmsListenerConfigUtils.JMS_LISTENER_ANNOTATION_PROCESSOR_BEAN_NAME);
  ctx.getBean(JmsListenerConfigUtils.JMS_LISTENER_ENDPOINT_REGISTRY_BEAN_NAME);
}
