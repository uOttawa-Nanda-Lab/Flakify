@Test public void testConstraintViolationException(){
  ConstraintViolationException violationException=mock(ConstraintViolationException.class);
  ConstraintViolation violation=mock(ConstraintViolation.class,Answers.RETURNS_DEEP_STUBS);
  given(violationException.getConstraintViolations()).willReturn(Sets.<ConstraintViolation<?>>newSet(violation));
  RpcException rpcException=new RpcException("violation",violationException);
  Response response=exceptionMapper.toResponse(rpcException);
  assertThat(response,not(nullValue()));
  assertThat(response.getEntity(),instanceOf(ViolationReport.class));
}
