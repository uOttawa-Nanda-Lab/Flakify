@Test public void rewriteUrl(){
  String url="http://test";
  String encodedUrl="http://test/session";
  String encodedSessionId="session.route";
  String sessionId="session";
  when(this.codec.encode(sessionId)).thenReturn(encodedSessionId);
  when(this.config.rewriteUrl(url,encodedSessionId)).thenReturn(encodedUrl);
  String result=this.subject.rewriteUrl(url,sessionId);
  assertSame(encodedUrl,result);
}
