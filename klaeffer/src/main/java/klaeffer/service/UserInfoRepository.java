package klaeffer.service;

import klaeffer.domain.user.UserInfo;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserInfoRepository extends CrudRepository<UserInfo,Integer> {
    @Query("SELECT user_info FROM klaeff_user where id = :id")
    Integer findUserInfoId(Integer id);

    @Query("SELECT * FROM user_info where id = :userInfoId")
    UserInfo findUserById(Integer userInfoId);
}
