@Test public void embeddedServiceWithCustomHornetQConfiguration(){
  load(CustomHornetQConfiguration.class);
  org.hornetq.core.config.Configuration configuration=this.context.getBean(org.hornetq.core.config.Configuration.class);
  assertEquals("customFooBar",configuration.getName());
}
