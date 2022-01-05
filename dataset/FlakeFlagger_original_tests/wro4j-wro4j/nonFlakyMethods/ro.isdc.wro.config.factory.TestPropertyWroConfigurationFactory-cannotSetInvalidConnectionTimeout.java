@Test(expected=WroRuntimeException.class) public void cannotSetInvalidConnectionTimeout(){
  final Properties props=new Properties();
  props.setProperty(ConfigConstants.connectionTimeout.name(),"9999999999999999999");
  factory=new PropertyWroConfigurationFactory(props);
  factory.create();
}
