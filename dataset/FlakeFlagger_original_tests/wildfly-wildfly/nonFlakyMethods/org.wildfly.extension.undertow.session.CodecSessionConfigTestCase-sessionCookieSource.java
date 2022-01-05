@Test public void sessionCookieSource(){
  HttpServerExchange exchange=new HttpServerExchange(null);
  SessionConfig.SessionCookieSource expected=SessionConfig.SessionCookieSource.OTHER;
  when(this.config.sessionCookieSource(exchange)).thenReturn(expected);
  SessionConfig.SessionCookieSource result=this.subject.sessionCookieSource(exchange);
  assertSame(expected,result);
}
