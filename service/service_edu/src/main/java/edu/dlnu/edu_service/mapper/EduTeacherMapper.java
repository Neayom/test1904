package edu.dlnu.edu_service.mapper;

import edu.dlnu.edu_service.entity.EduTeacher;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.data.domain.Page;

import java.util.Map;

/**
 * <p>
 * 讲师 Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2021-01-30
 */
public interface EduTeacherMapper extends BaseMapper<EduTeacher> {
    Page<EduTeacher> findByPaging(Map param);

}
