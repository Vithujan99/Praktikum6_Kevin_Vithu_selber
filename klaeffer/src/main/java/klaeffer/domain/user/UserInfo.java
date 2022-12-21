package klaeffer.domain.user;

import klaeffer.domain.shared.KlaeffUser;

public class UserInfo {
  private final KlaeffUser klaeffUser;
  private final UserName name;
  private final String image;

  public UserInfo(KlaeffUser klaeffUser, String name, String image) {
    this.klaeffUser = klaeffUser;
    this.name = new UserName(name);
    this.image = image;
  }

  public KlaeffUser getUser() {
    return klaeffUser;
  }

  public String getName() {
    return name.name();
  }

  public String getImage() {
    return image;
  }
}
