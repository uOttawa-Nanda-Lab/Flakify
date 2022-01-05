public void testMetaData(){
  ((ProcessEngineImpl)processEngine).getProcessEngineConfiguration().getCommandExecutor().execute(new Command<Object>(){
    public Object execute(    CommandContext commandContext){
      try {
        SqlSession sqlSession=commandContext.getDbSqlSession().getSqlSession();
        ResultSet tables=sqlSession.getConnection().getMetaData().getTables(null,null,null,null);
        while (tables.next()) {
          ResultSetMetaData resultSetMetaData=tables.getMetaData();
          int columnCount=resultSetMetaData.getColumnCount();
          for (int i=1; i <= columnCount; i++) {
            log.info("result set column {}|{}|{}|{}",i,resultSetMetaData.getColumnName(i),resultSetMetaData.getColumnLabel(i),tables.getString(i));
          }
          log.info("-------------------------------------------------------");
        }
      }
 catch (      Exception e) {
        e.printStackTrace();
      }
      return null;
    }
  }
);
}
