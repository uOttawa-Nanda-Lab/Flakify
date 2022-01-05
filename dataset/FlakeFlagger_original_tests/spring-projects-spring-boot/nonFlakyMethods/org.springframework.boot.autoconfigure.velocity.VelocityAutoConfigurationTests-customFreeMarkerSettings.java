@Test public void customFreeMarkerSettings(){
  registerAndRefreshContext("spring.velocity.properties.directive.parse.max.depth:10");
  assertThat(this.context.getBean(VelocityConfigurer.class).getVelocityEngine().getProperty("directive.parse.max.depth"),equalTo((Object)"10"));
}
