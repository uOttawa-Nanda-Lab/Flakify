@Test public void secure(){
  given(this.endpoint.invoke()).willReturn(new Health.Builder().up().withDetail("foo","bar").build());
  Object result=this.mvc.invoke(this.user);
  assertTrue(result instanceof Health);
  assertTrue(((Health)result).getStatus() == Status.UP);
  assertEquals("bar",((Health)result).getDetails().get("foo"));
}
