/** 
 * Test that the custom 500 error page is seen for an exception
 * @throws Exception
 */
@Test @OperateOnDeployment("error-producer.war") public void testExceptionError(@ArquillianResource(ErrorGeneratorServlet.class) URL baseURL) throws Exception {
  URL url=new URL(baseURL + "/ErrorGeneratorServlet");
  testURL(url,HttpURLConnection.HTTP_INTERNAL_ERROR,"500.jsp","java.lang.IllegalStateException");
}
