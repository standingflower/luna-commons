package com.luna.jpa.repository;

import com.luna.jpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * User Dao
 * </p>
 *
 * @package: com.luna.orm.jpa.repository
 * @description: User Dao
 * @author: yangkai.shen
 * @date: Created in 2018/11/7 14:07
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Repository
public interface UserDao extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    /**
     * jpql查询
     * 
     * @param name
     * @return
     */
    @Query(value = " from User where name = ?1")
    User findByJpql(String name);

    /**
     * jpql查询多条件查询
     *
     * @param name
     * @param id
     * @return
     */
    @Query(value = " from User where name = ?1 and id = ?2")
    User findByJpql2(String name, Long id);

    /**
     * 声明更新操作
     * 更新和删除需要添加事务操作支持 默认回滚操作 需要手动提交
     * 
     * @param newName
     * @param id
     * @return
     */
    @Query(value = " update User set name = ?1 where id = ?2 ")
    @Modifying
    int findByJpql3(String newName, long id);

    /**
     * nativeQuery true sql 查询
     * false jpql 查询
     * 
     * @return
     */
    @Query(value = "select * from orm_user", nativeQuery = true)
    List<Object[]> findByJpql4();

    /**
     * sql 查询规则
     * 
     * @param name
     * @return
     */
    @Query(value = "select * from orm_user where name like ?1%", nativeQuery = true)
    List<Object[]> findByJpql5(String name);

    /**
     * 方法名匹配规则
     * 
     * @param name
     * @param id
     * @return
     */
    User findByNameAndAndId(String name, long id);

    /**
     * 方法名模糊查询
     *
     * @param name
     * @return
     */
    List<User> findByNameLike(String name);

    /**
     * 状态值
     * 
     * @param status
     * @return
     */
    List<User> findByStatus(Integer status);
}
