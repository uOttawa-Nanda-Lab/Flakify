/** 
 * Test that the custom 500 error page is seen
 * @throws Exception
 */
@Test @OperateOnDeployment("error-producer.war") public void test500Error(@ArquillianResource(ErrorGeneratorServlet.class) URL baseURL) throws Exception {
  int errorCode=HttpURLConnection.HTTP_INTERNAL_ERROR;
  URL url=new URL(baseURL + "/ErrorGeneratorServlet?errorCode=" + errorCode);
  testURL(url,errorCode,"500.jsp",null);
}
