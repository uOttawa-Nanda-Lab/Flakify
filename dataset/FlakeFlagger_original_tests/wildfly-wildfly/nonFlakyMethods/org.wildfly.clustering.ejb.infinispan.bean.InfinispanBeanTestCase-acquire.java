@Test public void acquire(){
  Object value=new Object();
  when(this.group.getBean(this.id,this.listener)).thenReturn(value);
  Object result=this.bean.acquire();
  Assert.assertSame(value,result);
}
