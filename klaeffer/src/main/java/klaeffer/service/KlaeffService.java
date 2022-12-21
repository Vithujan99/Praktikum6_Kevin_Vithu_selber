package klaeffer.service;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import klaeffer.domain.klaeff.Klaeff;
import klaeffer.domain.shared.UserKlaeff;
import klaeffer.domain.user.UserInfo;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class KlaeffService {
  private final KlaeffRepository klaeffRepository;
  private final HashMap<Integer, UserInfo> userInfos = new HashMap<>();


  public KlaeffService(KlaeffRepository klaeffRepository) {
    this.klaeffRepository = klaeffRepository;
  }

  private void add(Klaeff klaeff) {
    klaeffRepository.save(klaeff);
  }

  public KlaeffPage getKlaeffs(int offset, int amount) {
    if (offset < 0) {
      offset = 0;
    }
    int start = offset * amount;
    while (start > klaeffRepository.size()) {
      start -= amount;
    }
    List<KlaeffDetail> klaeffsOnPage = klaeffRepository.findAll().stream()
        .skip(start)
        .limit(amount)
        .map(this::toDetail)
        .toList();
    boolean more = klaeffRepository.size() > start + amount;
    return new KlaeffPage(klaeffsOnPage, more);
  }

  private KlaeffDetail toDetail(Klaeff klaeff) {
    Integer id = klaeff.getUser().id();
    UserInfo userInfo = userInfo(id);
    return new KlaeffDetail(userInfo.getName(), klaeff.getContent(), id);
  }

  public UserInfo userInfo(Integer id) {
    return userInfos.get(id);
  }

  public void addKlaeff(OAuth2User principal, String text) {
    String login = principal.getAttribute("login");
    int id = Objects.requireNonNull(principal.getAttribute("id"));
    String image = principal.getAttribute("avatar_url");
    UserKlaeff userKlaeff = new UserKlaeff(id);
    userInfos.put(id, new UserInfo(userKlaeff, login, image));
    add(new Klaeff(userKlaeff, text));
  }
}
