@Test public void overridingOfPropertiesWorksAsExpected(){
  assertThat(this.foo,is(this.properties.getFoo()));
}
