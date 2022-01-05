@Test public void testGetMenuSuccessfully() throws Exception {
  url=url.addParameter(Constants.MERGER_KEY,".merge");
  given(invocation.getMethodName()).willReturn("getMenu");
  given(invocation.getParameterTypes()).willReturn(new Class<?>[]{});
  given(invocation.getArguments()).willReturn(new Object[]{});
  given(invocation.getAttachments()).willReturn(new HashMap<String,String>());
  given(invocation.getInvoker()).willReturn(firstInvoker);
  firstInvoker=(Invoker)Proxy.newProxyInstance(getClass().getClassLoader(),new Class<?>[]{Invoker.class},new InvocationHandler(){
    public Object invoke(    Object proxy,    Method method,    Object[] args) throws Throwable {
      if ("getUrl".equals(method.getName())) {
        return url.addParameter(Constants.GROUP_KEY,"first");
      }
      if ("getInterface".equals(method.getName())) {
        return MenuService.class;
      }
      if ("invoke".equals(method.getName())) {
        return new RpcResult(firstMenu);
      }
      return null;
    }
  }
);
  secondInvoker=(Invoker)Proxy.newProxyInstance(getClass().getClassLoader(),new Class<?>[]{Invoker.class},new InvocationHandler(){
    public Object invoke(    Object proxy,    Method method,    Object[] args) throws Throwable {
      if ("getUrl".equals(method.getName())) {
        return url.addParameter(Constants.GROUP_KEY,"second");
      }
      if ("getInterface".equals(method.getName())) {
        return MenuService.class;
      }
      if ("invoke".equals(method.getName())) {
        return new RpcResult(secondMenu);
      }
      return null;
    }
  }
);
  given(directory.list(invocation)).willReturn(new ArrayList(){
{
      add(firstInvoker);
      add(secondInvoker);
    }
  }
);
  given(directory.getUrl()).willReturn(url);
  given(directory.getInterface()).willReturn(MenuService.class);
  mergeableClusterInvoker=new MergeableClusterInvoker<MenuService>(directory);
  Result result=mergeableClusterInvoker.invoke(invocation);
  Assert.assertTrue(result.getValue() instanceof Menu);
  Menu menu=(Menu)result.getValue();
  Map<String,List<String>> expected=new HashMap<String,List<String>>();
  merge(expected,firstMenuMap);
  merge(expected,secondMenuMap);
  assertEquals(expected.keySet(),menu.getMenus().keySet());
  for (  String key : expected.keySet()) {
    List<String> values1=new ArrayList<String>(expected.get(key));
    List<String> values2=new ArrayList<String>(menu.getMenus().get(key));
    Collections.sort(values1);
    Collections.sort(values2);
    assertEquals(values1,values2);
  }
}
