/** 
 * Should throw  {@link NullPointerException} when provided requestHandler's collection is null.
 */
@Test(expected=NullPointerException.class) public void shouldNotAcceptNullRequestHandlers() throws Throwable {
  victim.setRequestHandlerFactory(new RequestHandlerFactory(){
    public Collection<RequestHandler> create(){
      return null;
    }
  }
);
  try {
    victim.doFilter(mockRequest,mockResponse,mockFilterChain);
  }
 catch (  final WroRuntimeException e) {
    throw e.getCause();
  }
}
