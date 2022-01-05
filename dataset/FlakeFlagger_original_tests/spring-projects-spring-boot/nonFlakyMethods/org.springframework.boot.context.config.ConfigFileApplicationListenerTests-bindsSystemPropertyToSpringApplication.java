@Test public void bindsSystemPropertyToSpringApplication() throws Exception {
  System.setProperty("spring.main.showBanner","false");
  this.initializer.onApplicationEvent(this.event);
  SpringApplication application=this.event.getSpringApplication();
  Field field=ReflectionUtils.findField(SpringApplication.class,"showBanner");
  field.setAccessible(true);
  assertThat((Boolean)field.get(application),equalTo(false));
}
