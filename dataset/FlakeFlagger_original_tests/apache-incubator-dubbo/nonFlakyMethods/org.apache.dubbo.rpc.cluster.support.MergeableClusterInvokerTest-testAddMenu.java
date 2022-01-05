@Test public void testAddMenu() throws Exception {
  String menu="first";
  List<String> menuItems=new ArrayList<String>(){
{
      add("1");
      add("2");
    }
  }
;
  given(invocation.getMethodName()).willReturn("addMenu");
  given(invocation.getParameterTypes()).willReturn(new Class<?>[]{String.class,List.class});
  given(invocation.getArguments()).willReturn(new Object[]{menu,menuItems});
  given(invocation.getAttachments()).willReturn(new HashMap<String,String>());
  given(invocation.getInvoker()).willReturn(firstInvoker);
  given(firstInvoker.getUrl()).willReturn(url.addParameter(Constants.GROUP_KEY,"first"));
  given(firstInvoker.getInterface()).willReturn(MenuService.class);
  given(firstInvoker.invoke(invocation)).willReturn(new RpcResult());
  given(firstInvoker.isAvailable()).willReturn(true);
  given(secondInvoker.getUrl()).willReturn(url.addParameter(Constants.GROUP_KEY,"second"));
  given(secondInvoker.getInterface()).willReturn(MenuService.class);
  given(secondInvoker.invoke(invocation)).willReturn(new RpcResult());
  given(secondInvoker.isAvailable()).willReturn(true);
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
  Assert.assertNull(result.getValue());
}
