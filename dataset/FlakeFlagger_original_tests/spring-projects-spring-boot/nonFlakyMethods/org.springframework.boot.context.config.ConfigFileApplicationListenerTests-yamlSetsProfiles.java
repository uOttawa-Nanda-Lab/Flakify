@Test public void yamlSetsProfiles() throws Exception {
  this.initializer.setSearchNames("testsetprofiles");
  this.initializer.onApplicationEvent(this.event);
  assertEquals("dev",StringUtils.arrayToCommaDelimitedString(this.environment.getActiveProfiles()));
  String property=this.environment.getProperty("my.property");
  assertThat(Arrays.asList(this.environment.getActiveProfiles()),contains("dev"));
  assertThat(property,equalTo("fromdevprofile"));
  ConfigurationPropertySources propertySource=(ConfigurationPropertySources)this.environment.getPropertySources().get("applicationConfigurationProperties");
  Collection<org.springframework.core.env.PropertySource<?>> sources=propertySource.getSource();
  assertEquals(2,sources.size());
  List<String> names=new ArrayList<String>();
  for (  org.springframework.core.env.PropertySource<?> source : sources) {
    if (source instanceof EnumerableCompositePropertySource) {
      for (      org.springframework.core.env.PropertySource<?> nested : ((EnumerableCompositePropertySource)source).getSource()) {
        names.add(nested.getName());
      }
    }
 else {
      names.add(source.getName());
    }
  }
  assertThat(names,contains("applicationConfig: [classpath:/testsetprofiles.yml]#dev","applicationConfig: [classpath:/testsetprofiles.yml]"));
}
