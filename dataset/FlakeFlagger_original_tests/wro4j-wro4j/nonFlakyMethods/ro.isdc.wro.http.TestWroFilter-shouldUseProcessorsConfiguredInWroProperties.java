@Test public void shouldUseProcessorsConfiguredInWroProperties() throws Exception {
  final ObjectFactory<WroConfiguration> configurationFactory=new PropertiesAndFilterConfigWroConfigurationFactory(mockFilterConfig){
    @Override public Properties createProperties(){
      final Properties props=new Properties();
      props.setProperty(ConfigConstants.managerFactoryClassName.name(),ConfigurableWroManagerFactory.class.getName());
      props.setProperty(ConfigurableProcessorsFactory.PARAM_PRE_PROCESSORS,CssMinProcessor.ALIAS);
      return props;
    }
  }
;
  victim.setWroConfigurationFactory(configurationFactory);
  victim.setWroManagerFactory(null);
  victim.init(mockFilterConfig);
  Context.set(Context.webContext(mockRequest,mockResponse,mockFilterConfig),configurationFactory.create());
  final WroManagerFactory factory=victim.getWroManagerFactory();
  assertEquals(1,factory.create().getProcessorsFactory().getPreProcessors().size());
}
