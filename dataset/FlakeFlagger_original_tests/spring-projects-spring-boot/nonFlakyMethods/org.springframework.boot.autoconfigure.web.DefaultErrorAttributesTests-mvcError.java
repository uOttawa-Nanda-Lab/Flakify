@Test public void mvcError() throws Exception {
  RuntimeException ex=new RuntimeException("Test");
  ModelAndView modelAndView=this.errorAttributes.resolveException(this.request,null,null,ex);
  this.request.setAttribute("javax.servlet.error.exception",new RuntimeException("Ignored"));
  Map<String,Object> attributes=this.errorAttributes.getErrorAttributes(this.requestAttributes,false);
  assertThat(this.errorAttributes.getError(this.requestAttributes),sameInstance((Object)ex));
  assertThat(modelAndView,nullValue());
  assertThat(attributes.get("exception"),equalTo((Object)RuntimeException.class.getName()));
  assertThat(attributes.get("message"),equalTo((Object)"Test"));
}
