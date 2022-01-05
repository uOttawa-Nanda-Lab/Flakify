@Test public void missingStatusCode() throws Exception {
  Map<String,Object> attributes=this.errorAttributes.getErrorAttributes(this.requestAttributes,false);
  assertThat(attributes.get("error"),equalTo((Object)"None"));
  assertThat(attributes.get("status"),equalTo((Object)999));
}
