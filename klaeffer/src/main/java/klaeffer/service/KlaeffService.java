package klaeffer.service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import klaeffer.domain.klaeff.Klaeff;
import klaeffer.domain.shared.User;
import klaeffer.domain.user.UserInfo;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class KlaeffService {
  private final LinkedList<Klaeff> klaeffs = new LinkedList<>();
  private final HashMap<Integer, UserInfo> userInfos = new HashMap<>();


  private void add(Klaeff klaeff) {
    klaeffs.addFirst(klaeff);
  }

  public KlaeffPage getKlaeffs(int offset, int amount) {
    if (offset < 0) {
      offset = 0;
    }
    int start = offset * amount;
    while (start > klaeffs.size()) {
      start -= amount;
    }
    List<KlaeffDetail> klaeffsOnPage = klaeffs.stream()
        .skip(start)
        .limit(amount)
        .map(this::toDetail)
        .toList();
    boolean more = klaeffs.size() > start + amount;
    return new KlaeffPage(klaeffsOnPage, more);
  }

  private KlaeffDetail toDetail(Klaeff klaeff) {
    Integer id = klaeff.getUser().getId();
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
    User user = new User(id);
    userInfos.put(id, new UserInfo(user, login, image));
    add(new Klaeff(user, text));
  }
}
