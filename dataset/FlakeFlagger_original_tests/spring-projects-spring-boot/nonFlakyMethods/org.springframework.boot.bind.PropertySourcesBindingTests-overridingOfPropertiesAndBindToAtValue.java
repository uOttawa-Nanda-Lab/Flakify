@Test public void overridingOfPropertiesAndBindToAtValue(){
  assertThat(this.foo,is(this.properties.getFoo()));
}
