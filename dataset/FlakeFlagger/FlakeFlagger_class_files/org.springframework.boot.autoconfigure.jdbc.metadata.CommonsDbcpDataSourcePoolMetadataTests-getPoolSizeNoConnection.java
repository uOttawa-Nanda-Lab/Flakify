package org.springframework.boot.autoconfigure.jdbc.metadata;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class CommonsDbcpDataSourcePoolMetadataTests {
@Test public void getPoolSizeNoConnection(){
  JdbcTemplate jdbcTemplate=new JdbcTemplate(getDataSourceMetadata().getDataSource());
  jdbcTemplate.execute(new ConnectionCallback<Void>(){
    @Override public Void doInConnection(    Connection connection) throws SQLException, DataAccessException {
      return null;
    }
  }
);
  assertEquals(Integer.valueOf(0),getDataSourceMetadata().getActive());
  assertEquals(Float.valueOf(0),getDataSourceMetadata().getUsage());
}

}