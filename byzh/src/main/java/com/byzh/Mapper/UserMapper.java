package com.byzh.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.byzh.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.io.File;

/**
 * @Author: YR, DYQ
 * @Description: 用户相关操作Mapper层
 * @Date: Update in 2022/3/20
 */

@Mapper
public interface UserMapper extends BaseMapper<User> {

    //通过学生ID和密码获取用户对象·
    @Select("select * from user where id = #{id} and pwd = #{pwd} ;")
    User getByIdAndPwd (String id,String pwd);

    //新建用户
    @Insert("insert into user (id,nickname,gender,qq,wechat,pwd,major,campus,heading) values(#{id},#{nickname},#{gender},#{qq},#{wechat},#{pwd},#{major},#{campus},#{heading})")
    boolean register(User user);

    //通过用户ID获取用户昵称
    @Select("select nickname from user where user.id = #{uid};")
    String getNickNameByUid(String uid);

    //上传头像
    @Update("UPDATE user SET heading = #{url} WHERE user.id = #{uid};")
    boolean updateProfileByUid(String url,String uid);

    //同步帖子heading
    @Update("UPDATE post SET heading = #{url} WHERE poster_id = #{uid};")
    boolean updatePostProfileByUid(String url,String uid);

    //通过用户ID获取用户heading
    @Select("select heading from user where user.id = #{uid};")
    String getHeadingByUid(String uid);


}
