@Test public void getRequiredProperty() throws Exception {
  assertThat(this.resolver.getRequiredProperty("my-string"),equalTo("value"));
  this.thrown.expect(IllegalStateException.class);
  this.thrown.expectMessage("required key [my-missing] not found");
  this.resolver.getRequiredProperty("my-missing");
}
