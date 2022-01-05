@Test public void disabled() throws Exception {
  SpringApplication application=new SpringApplication(Config.class);
  application.setWebEnvironment(false);
  Map<String,Object> props=new HashMap<String,Object>();
  props.put("spring.output.ansi.enabled","never");
  application.setDefaultProperties(props);
  application.run();
  assertThat(AnsiOutput.getEnabled(),equalTo(Enabled.NEVER));
}
