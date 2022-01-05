@Test public void shouldNotFailWhenEachHandlerIsInvoked(){
  final Map<String,RequestHandler> map=victim.provideRequestHandlers();
  for (  final RequestHandler handler : map.values()) {
    WroTestUtils.createInjector().inject(handler);
    handler.accept(mockRequest);
    handler.isEnabled();
  }
}
