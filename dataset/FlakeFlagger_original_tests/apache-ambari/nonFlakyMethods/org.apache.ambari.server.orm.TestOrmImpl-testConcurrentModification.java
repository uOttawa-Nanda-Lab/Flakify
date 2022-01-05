@Test public void testConcurrentModification() throws InterruptedException {
  final ClusterDAO clusterDAO=injector.getInstance(ClusterDAO.class);
  ClusterEntity clusterEntity=new ClusterEntity();
  clusterEntity.setClusterName("cluster1");
  clusterDAO.create(clusterEntity);
  clusterEntity=clusterDAO.findById(clusterEntity.getClusterId());
  assertEquals("cluster1",clusterEntity.getClusterName());
  Thread thread=new Thread(){
    @Override public void run(){
      ClusterEntity clusterEntity1=clusterDAO.findByName("cluster1");
      clusterEntity1.setClusterName("anotherName");
      clusterDAO.merge(clusterEntity1);
    }
  }
;
  thread.start();
  thread.join();
  clusterEntity=clusterDAO.findById(clusterEntity.getClusterId());
  assertEquals("anotherName",clusterEntity.getClusterName());
  thread=new Thread(){
    @Override public void run(){
      clusterDAO.removeByName("anotherName");
    }
  }
;
  thread.start();
  thread.join();
  assertNull(clusterDAO.findById(clusterEntity.getClusterId()));
  List<ClusterEntity> result=clusterDAO.findAll();
  thread=new Thread(){
    @Override public void run(){
      ClusterEntity temp=new ClusterEntity();
      temp.setClusterName("temp_cluster");
      clusterDAO.create(temp);
    }
  }
;
  thread.start();
  thread.join();
  assertEquals(result.size() + 1,(result=clusterDAO.findAll()).size());
  thread=new Thread(){
    @Override public void run(){
      ClusterEntity temp=new ClusterEntity();
      temp.setClusterName("temp_cluster2");
      clusterDAO.create(temp);
    }
  }
;
  thread.start();
  thread.join();
  assertEquals(result.size() + 1,(clusterDAO.findAll()).size());
}
