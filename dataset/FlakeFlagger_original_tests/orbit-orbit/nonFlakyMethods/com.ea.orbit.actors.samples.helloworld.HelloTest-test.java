@Test public void test(){
  final String clusterName="helloWorldTestCluster." + System.currentTimeMillis();
  OrbitStage stage1=initStage(clusterName,"stage1");
  OrbitStage stage2=initStage(clusterName,"stage2");
  System.out.println("Stages initialized");
  IHello helloFrom1=stage1.getReference(IHello.class,"0");
  IHello helloFrom2=stage2.getReference(IHello.class,"0");
  System.out.println(helloFrom1.sayHello("Hi from 01").join());
  System.out.println(helloFrom2.sayHello("Hi from 02").join());
}
