@Test public void specificStatusCode() throws Exception {
  this.request.setAttribute("javax.servlet.error.status_code",404);
  Map<String,Object> attributes=this.errorAttributes.getErrorAttributes(this.requestAttributes,false);
  assertThat(attributes.get("error"),equalTo((Object)HttpStatus.NOT_FOUND.getReasonPhrase()));
  assertThat(attributes.get("status"),equalTo((Object)404));
}
