@Test public void overridingOfPropertiesOrderOfApplicationProperties(){
  assertThat(this.properties.getFoo(),is("bucket"));
}
