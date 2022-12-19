package klaeffer.domain.user;

import klaeffer.domain.shared.User;

public class UserInfo {
  private final User user;
  private final UserName name;
  private final String image;

  public UserInfo(User user, String name, String image) {
    this.user = user;
    this.name = new UserName(name);
    this.image = image;
  }

  public User getUser() {
    return user;
  }

  public String getName() {
    return name.name();
  }

  public String getImage() {
    return image;
  }
}
