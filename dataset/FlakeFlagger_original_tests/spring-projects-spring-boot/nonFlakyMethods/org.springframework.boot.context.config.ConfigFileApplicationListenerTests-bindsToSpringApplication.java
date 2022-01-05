@Test public void bindsToSpringApplication() throws Exception {
  this.initializer.setSearchNames("bindtoapplication");
  this.initializer.onApplicationEvent(this.event);
  SpringApplication application=this.event.getSpringApplication();
  Field field=ReflectionUtils.findField(SpringApplication.class,"showBanner");
  field.setAccessible(true);
  assertThat((Boolean)field.get(application),equalTo(false));
}
