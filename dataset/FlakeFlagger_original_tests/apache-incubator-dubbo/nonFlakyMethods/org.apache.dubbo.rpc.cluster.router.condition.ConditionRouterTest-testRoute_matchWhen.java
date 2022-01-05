@Test public void testRoute_matchWhen(){
  Invocation invocation=new RpcInvocation();
  Router router=new ConditionRouterFactory().getRouter(getRouteUrl(" => host = 1.2.3.4"));
  boolean matchWhen=((ConditionRouter)router).matchWhen(URL.valueOf("consumer://1.1.1.1/com.foo.BarService"),invocation);
  Assert.assertEquals(true,matchWhen);
  router=new ConditionRouterFactory().getRouter(getRouteUrl("host = 2.2.2.2,1.1.1.1,3.3.3.3 => host = 1.2.3.4"));
  matchWhen=((ConditionRouter)router).matchWhen(URL.valueOf("consumer://1.1.1.1/com.foo.BarService"),invocation);
  Assert.assertEquals(true,matchWhen);
  router=new ConditionRouterFactory().getRouter(getRouteUrl("host = 2.2.2.2,1.1.1.1,3.3.3.3 & host !=1.1.1.1 => host = 1.2.3.4"));
  matchWhen=((ConditionRouter)router).matchWhen(URL.valueOf("consumer://1.1.1.1/com.foo.BarService"),invocation);
  Assert.assertEquals(false,matchWhen);
  router=new ConditionRouterFactory().getRouter(getRouteUrl("host !=4.4.4.4 & host = 2.2.2.2,1.1.1.1,3.3.3.3 => host = 1.2.3.4"));
  matchWhen=((ConditionRouter)router).matchWhen(URL.valueOf("consumer://1.1.1.1/com.foo.BarService"),invocation);
  Assert.assertEquals(true,matchWhen);
  router=new ConditionRouterFactory().getRouter(getRouteUrl("host !=4.4.4.* & host = 2.2.2.2,1.1.1.1,3.3.3.3 => host = 1.2.3.4"));
  matchWhen=((ConditionRouter)router).matchWhen(URL.valueOf("consumer://1.1.1.1/com.foo.BarService"),invocation);
  Assert.assertEquals(true,matchWhen);
  router=new ConditionRouterFactory().getRouter(getRouteUrl("host = 2.2.2.2,1.1.1.*,3.3.3.3 & host != 1.1.1.1 => host = 1.2.3.4"));
  matchWhen=((ConditionRouter)router).matchWhen(URL.valueOf("consumer://1.1.1.1/com.foo.BarService"),invocation);
  Assert.assertEquals(false,matchWhen);
  router=new ConditionRouterFactory().getRouter(getRouteUrl("host = 2.2.2.2,1.1.1.*,3.3.3.3 & host != 1.1.1.2 => host = 1.2.3.4"));
  matchWhen=((ConditionRouter)router).matchWhen(URL.valueOf("consumer://1.1.1.1/com.foo.BarService"),invocation);
  Assert.assertEquals(true,matchWhen);
}
