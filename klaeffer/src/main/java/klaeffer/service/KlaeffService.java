package klaeffer.service;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import klaeffer.domain.klaeff.Klaeff;
import klaeffer.domain.shared.KlaeffUser;
import klaeffer.domain.user.UserInfo;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class KlaeffService {

  private final KlaeffRepository klaeffRepository;
  private final UserInfoRepository userInfoRepository;
//  private final HashMap<Integer, UserInfo> userInfos = new HashMap<>();

  public KlaeffService(KlaeffRepository klaeffRepository, UserInfoRepository userInfoRepository) {
    this.klaeffRepository = klaeffRepository;
    this.userInfoRepository = userInfoRepository;
  }


  private void add(Klaeff klaeff) {
    klaeffRepository.save(klaeff);
  }

  public KlaeffPage getKlaeffs(int offset, int amount) {
    if (offset < 0) {
      offset = 0;
    }
    int start = offset * amount;
    while (start > klaeffRepository.findAll().size()) {
      start -= amount;
    }
    List<KlaeffDetail> klaeffsOnPage = klaeffRepository.findAll().stream()
        .skip(start)
        .limit(amount)
        .map(this::toDetail)
        .toList();
    boolean more = klaeffRepository.findAll().size() > start + amount;
    return new KlaeffPage(klaeffsOnPage, more);
  }

  private KlaeffDetail toDetail(Klaeff klaeff) {
    Integer id = klaeff.getUser().getId();
    UserInfo userInfo = userInfo(id);
    return new KlaeffDetail(userInfo.getName(), klaeff.getContent(), id);
  }

  public UserInfo userInfo(Integer id) {
    Integer userInfoId = userInfoRepository.findUserInfoId(id);
    return userInfoRepository.findUserById(userInfoId);
  }

  public void addKlaeff(OAuth2User principal, String text) {
    String login = principal.getAttribute("login");
    int id = Objects.requireNonNull(principal.getAttribute("id"));
    String image = principal.getAttribute("avatar_url");
    KlaeffUser klaeffUser = new KlaeffUser(id);
    userInfoRepository.save(new UserInfo(klaeffUser, login, image));
    add(new Klaeff(klaeffUser, text));
  }
}
