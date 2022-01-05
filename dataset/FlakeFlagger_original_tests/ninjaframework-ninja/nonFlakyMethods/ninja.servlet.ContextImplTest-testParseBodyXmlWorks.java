/** 
 * This is the default mode. We get a Content-Type: application/json and want to parse the incoming json.
 */
@Test public void testParseBodyXmlWorks(){
  when(httpServletRequest.getContentType()).thenReturn("application/xml");
  context.init(httpServletRequest,httpServletResponse);
  when(bodyParserEngineManager.getBodyParserEngineForContentType("application/xml")).thenReturn(bodyParserEngine);
  when(bodyParserEngine.invoke(context,Dummy.class)).thenReturn(new Dummy());
  Object o=context.parseBody(Dummy.class);
  verify(bodyParserEngineManager).getBodyParserEngineForContentType("application/xml");
  assertTrue(o instanceof Dummy);
}
