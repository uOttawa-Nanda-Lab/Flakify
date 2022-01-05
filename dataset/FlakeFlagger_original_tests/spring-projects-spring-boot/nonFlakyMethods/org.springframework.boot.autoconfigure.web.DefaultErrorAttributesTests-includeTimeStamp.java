@Test public void includeTimeStamp() throws Exception {
  Map<String,Object> attributes=this.errorAttributes.getErrorAttributes(this.requestAttributes,false);
  assertThat(attributes.get("timestamp"),instanceOf(Date.class));
}
