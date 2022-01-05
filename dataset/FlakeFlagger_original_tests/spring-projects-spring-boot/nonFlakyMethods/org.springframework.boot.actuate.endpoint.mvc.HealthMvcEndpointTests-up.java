@Test public void up(){
  given(this.endpoint.invoke()).willReturn(new Health.Builder().up().build());
  Object result=this.mvc.invoke(null);
  assertTrue(result instanceof Health);
  assertTrue(((Health)result).getStatus() == Status.UP);
}
