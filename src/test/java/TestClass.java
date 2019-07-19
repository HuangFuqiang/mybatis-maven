import com.code.mybatismaven.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class TestClass {

    SqlSessionFactory sqlSessionFactory;

    @Before
    public void initFactory() throws IOException {
        String resource = "mybatis-config.xml";

        InputStream inputStream = Resources.getResourceAsStream(resource);

        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Test
    public void testListAll() {
        SqlSession session = sqlSessionFactory.openSession();
        User recode = new User();
        recode.setName("user3");
        session.insert("com.code.mybatismaven.UserMapper.insertRecode", recode);

        List<User> userList = session.selectList("com.code.mybatismaven.UserMapper.selectAll");
        for (User user : userList) {
            System.out.println("id=" + user.getId() + ", name=" + user.getName() + ", createTime=" + user.getCreateTime());
        }
    }
}
