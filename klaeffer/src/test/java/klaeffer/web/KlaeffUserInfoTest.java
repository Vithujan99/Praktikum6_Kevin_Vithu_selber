package klaeffer.web;

import static org.assertj.core.api.Assertions.assertThat;


import java.util.List;
import java.util.Map;

import klaeffer.service.KlaeffRepository;
import klaeffer.service.KlaeffService;
import klaeffer.service.UserInfoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;


@JdbcTest
public class KlaeffUserInfoTest {

  @Autowired
  KlaeffRepository klaeffRepository;
  UserInfoRepository userInfoRepository;

  @Test
  @DisplayName("Die Userinfos werden aus dem Token extrahiert")
  void test_() {
    DefaultOAuth2User user = new DefaultOAuth2User(List.of(),
        Map.of("id", 2,
            "login", "LarryLipinsky",
            "avatar_url", "https://www.omdb.org/image/default/20652.jpeg?v=2")
        , "id");

    KlaeffService service = new KlaeffService(klaeffRepository,userInfoRepository);
    service.addKlaeff(user, "*sniff* Müller?!?");
    assertThat(service.userInfo(2).getImage()).isEqualTo(
        "https://www.omdb.org/image/default/20652.jpeg?v=2");
  }

}
