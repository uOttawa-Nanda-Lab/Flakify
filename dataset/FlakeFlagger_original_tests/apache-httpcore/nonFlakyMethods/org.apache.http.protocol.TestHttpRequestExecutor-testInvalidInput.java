@Test public void testInvalidInput() throws Exception {
  HttpContext context=new BasicHttpContext();
  HttpRequest request=new BasicHttpRequest("GET","/");
  HttpResponse response=new BasicHttpResponse(HttpVersion.HTTP_1_1,200,"OK");
  HttpClientConnection conn=Mockito.mock(HttpClientConnection.class);
  HttpProcessor httprocessor=Mockito.mock(HttpProcessor.class);
  try {
    HttpRequestExecutor executor=new HttpRequestExecutor();
    executor.execute(null,conn,context);
    Assert.fail("IllegalArgumentException expected");
  }
 catch (  IllegalArgumentException expected) {
  }
  try {
    HttpRequestExecutor executor=new HttpRequestExecutor();
    executor.execute(request,null,context);
    Assert.fail("IllegalArgumentException expected");
  }
 catch (  IllegalArgumentException expected) {
  }
  try {
    HttpRequestExecutor executor=new HttpRequestExecutor();
    executor.execute(request,conn,null);
    Assert.fail("IllegalArgumentException expected");
  }
 catch (  IllegalArgumentException expected) {
  }
  try {
    HttpRequestExecutor executor=new HttpRequestExecutor();
    executor.doSendRequest(null,conn,context);
    Assert.fail("IllegalArgumentException expected");
  }
 catch (  IllegalArgumentException expected) {
  }
  try {
    HttpRequestExecutor executor=new HttpRequestExecutor();
    executor.doSendRequest(request,null,context);
    Assert.fail("IllegalArgumentException expected");
  }
 catch (  IllegalArgumentException expected) {
  }
  try {
    HttpRequestExecutor executor=new HttpRequestExecutor();
    executor.doSendRequest(request,conn,null);
    Assert.fail("IllegalArgumentException expected");
  }
 catch (  IllegalArgumentException expected) {
  }
  try {
    HttpRequestExecutor executor=new HttpRequestExecutor();
    executor.doReceiveResponse(null,conn,context);
    Assert.fail("IllegalArgumentException expected");
  }
 catch (  IllegalArgumentException expected) {
  }
  try {
    HttpRequestExecutor executor=new HttpRequestExecutor();
    executor.doReceiveResponse(request,null,context);
    Assert.fail("IllegalArgumentException expected");
  }
 catch (  IllegalArgumentException expected) {
  }
  try {
    HttpRequestExecutor executor=new HttpRequestExecutor();
    executor.doReceiveResponse(request,conn,null);
    Assert.fail("IllegalArgumentException expected");
  }
 catch (  IllegalArgumentException expected) {
  }
  try {
    HttpRequestExecutor executor=new HttpRequestExecutor();
    executor.preProcess(null,httprocessor,context);
    Assert.fail("IllegalArgumentException expected");
  }
 catch (  IllegalArgumentException expected) {
  }
  try {
    HttpRequestExecutor executor=new HttpRequestExecutor();
    executor.preProcess(request,null,context);
    Assert.fail("IllegalArgumentException expected");
  }
 catch (  IllegalArgumentException expected) {
  }
  try {
    HttpRequestExecutor executor=new HttpRequestExecutor();
    executor.preProcess(request,httprocessor,null);
    Assert.fail("IllegalArgumentException expected");
  }
 catch (  IllegalArgumentException expected) {
  }
  try {
    HttpRequestExecutor executor=new HttpRequestExecutor();
    executor.postProcess(null,httprocessor,context);
    Assert.fail("IllegalArgumentException expected");
  }
 catch (  IllegalArgumentException expected) {
  }
  try {
    HttpRequestExecutor executor=new HttpRequestExecutor();
    executor.postProcess(response,null,context);
    Assert.fail("IllegalArgumentException expected");
  }
 catch (  IllegalArgumentException expected) {
  }
  try {
    HttpRequestExecutor executor=new HttpRequestExecutor();
    executor.postProcess(response,httprocessor,null);
    Assert.fail("IllegalArgumentException expected");
  }
 catch (  IllegalArgumentException expected) {
  }
}
