/** 
 * Test that the custom 404 error page is seen
 * @throws Exception
 */
@Test @OperateOnDeployment("error-producer.war") public void test404Error(@ArquillianResource(ErrorGeneratorServlet.class) URL baseURL) throws Exception {
  int errorCode=HttpURLConnection.HTTP_NOT_FOUND;
  URL url=new URL(baseURL + "/ErrorGeneratorServlet?errorCode=" + errorCode);
  testURL(url,errorCode,"404.jsp",null);
}
