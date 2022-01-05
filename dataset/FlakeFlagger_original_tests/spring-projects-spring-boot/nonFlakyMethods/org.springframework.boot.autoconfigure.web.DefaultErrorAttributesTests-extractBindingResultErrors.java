@Test public void extractBindingResultErrors() throws Exception {
  BindingResult bindingResult=new MapBindingResult(Collections.singletonMap("a","b"),"objectName");
  bindingResult.addError(new ObjectError("c","d"));
  BindException ex=new BindException(bindingResult);
  this.request.setAttribute("javax.servlet.error.exception",ex);
  Map<String,Object> attributes=this.errorAttributes.getErrorAttributes(this.requestAttributes,false);
  assertThat(attributes.get("message"),equalTo((Object)("Validation failed for " + "object='objectName'. Error count: 1")));
  assertThat(attributes.get("errors"),equalTo((Object)bindingResult.getAllErrors()));
}
