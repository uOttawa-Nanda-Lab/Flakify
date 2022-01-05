@Test public void getRequiredPropertyWithType() throws Exception {
  assertThat(this.resolver.getRequiredProperty("my-integer",Integer.class),equalTo(123));
  this.thrown.expect(IllegalStateException.class);
  this.thrown.expectMessage("required key [my-missing] not found");
  this.resolver.getRequiredProperty("my-missing",Integer.class);
}
