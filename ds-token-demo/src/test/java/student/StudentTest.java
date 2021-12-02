package student;

import com.guang.demo.TestApplication;
import com.guang.demo.mapper.base.student.StudentMapper;
import com.guang.demo.pojo.entity.DsStudent;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author guangyong.deng
 * @date 2021-12-01 9:34
 */

@SpringBootTest(classes = TestApplication.class)
public class StudentTest {

    @Autowired
    StudentMapper studentMapper;


    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<DsStudent> userList = studentMapper.selectList(null);
        userList.forEach(System.out::println);
    }
}
