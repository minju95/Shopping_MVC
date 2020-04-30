package loader;

import javax.servlet.http.HttpServlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import java.util.StringTokenizer;

public class DBCPInit extends HttpServlet{
	public void init(ServletConfig config) throws ServletException {
		try { //DBMS와 연결할 때 사용될 JDBC 드라이버 로딩
			String drivers = config.getInitParameter("jdbcdriver");
			StringTokenizer st = new StringTokenizer(drivers, ",");
			while(st.hasMoreTokens()) {
				String jdbcDriver = st.nextToken();
				Class.forName(jdbcDriver);
			}
		Class.forName("org.apache.commons.dbcp.PoolingDriver");
		//DBCP API에서 풀 기능을 제공하기 위해 사용되는 PoolingDriver 로딩
		
		} catch(Exception ex) {
			throw new ServletException(ex);
		}
		
	}
}
