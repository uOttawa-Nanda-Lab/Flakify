@Test public void test1(){
  MockBean.reset();
  StatelessObjectFactory<MockBean> factory=new MockFactory();
  Pool<MockBean> pool=new StrictMaxPool<MockBean>(factory,10,1,TimeUnit.SECONDS);
  pool.start();
  MockBean[] beans=new MockBean[10];
  for (int i=0; i < beans.length; i++) {
    beans[i]=pool.get();
  }
  for (int i=0; i < beans.length; i++) {
    pool.release(beans[i]);
    beans[i]=null;
  }
  pool.stop();
  assertEquals(10,MockBean.getPostConstructs());
  assertEquals(10,MockBean.getPreDestroys());
}
