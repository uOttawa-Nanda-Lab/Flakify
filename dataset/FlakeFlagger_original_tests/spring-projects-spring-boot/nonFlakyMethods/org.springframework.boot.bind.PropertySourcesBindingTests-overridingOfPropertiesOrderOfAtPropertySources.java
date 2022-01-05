@Test public void overridingOfPropertiesOrderOfAtPropertySources(){
  assertThat(this.properties.getBar(),is("override"));
}
