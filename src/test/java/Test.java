import com.Dao.TestFind;
import com.domain.Dept;
import com.domain.Emp;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class Test {

    private SqlSession session;
    private InputStream in;
    private TestFind testFind;
    @Before
    public void init() throws IOException {
        in = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //openSession(true) 表示自动提交事务
        session = factory.openSession(true);
        testFind = session.getMapper(TestFind.class);

    }
    @After
    public void Destroy() throws IOException {
        session.close();
        in.close();
    }

    //一对多查询，所有用户信息中需要用集合封装其对应的多条数据
    @org.junit.Test
    public void getImf() {
        List<Dept> depts = testFind.findByDeptno();

        for (Dept value : depts) {
//            System.out.println(value.getEmpList());
//            System.out.println(user1.getAccounts());
            List<Emp> empList = value.getEmpList();
            for (Emp emp : empList) {
                System.out.println(emp);
            }
        }
    }
}
