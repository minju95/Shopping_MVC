package loader;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * -간단한 close, rollback() 처리를 위한 클래스
 * -Connection, Statement, ResultSet과 같은 클래스들은 사용이 끝나면 close() 메소드를 호출하여 자원을 반환해야 함
 * -close() 메소드를 매번 작성해야 하는 번거로움을 줄이기 위해 이 클래스 생성
 * */
public class JdbcUtil {
	public static void close(ResultSet rs) {
		if(rs != null) {
			try {
				rs.close();
			} catch(SQLException ex) {	
			}
		}
	}
	
	public static void close(Statement stmt) {
		if(stmt != null) {
			try {
				stmt.close();
			} catch(SQLException ex) {
			}
		}
	}
	
	public static void close(Connection conn) {
		if(conn != null) {
			try {
				conn.close();
			} catch(SQLException ex) {
			}
		}		
	}
	
	public static void rollback(Connection conn) {
		if( conn != null) {
			try {
				conn.close();
			} catch(SQLException ex) {
			}
		}
	}
	
	
}

