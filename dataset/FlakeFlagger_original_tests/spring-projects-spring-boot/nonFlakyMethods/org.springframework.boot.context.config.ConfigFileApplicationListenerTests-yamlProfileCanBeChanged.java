@Test public void yamlProfileCanBeChanged() throws Exception {
  EnvironmentTestUtils.addEnvironment(this.environment,"spring.profiles.active:prod");
  this.initializer.setSearchNames("testsetprofiles");
  this.initializer.onApplicationEvent(this.event);
  assertThat(this.environment.getActiveProfiles(),equalTo(new String[]{"prod"}));
}
