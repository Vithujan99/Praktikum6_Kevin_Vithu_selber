package klaeffer.domain.user;

import klaeffer.domain.shared.UserKlaeff;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;

public class UserInfo {
  @Id
  private final Integer id; // new
  private final UserKlaeff userKlaeff;
  private final UserName name;
  private final String image;

  @PersistenceCreator
  public UserInfo(Integer id, UserKlaeff userKlaeff, String name, String image) {
    this.id = id;
    this.userKlaeff = userKlaeff;
    this.name = new UserName(name);
    this.image = image;
  }

  public UserInfo(UserKlaeff userKlaeff, String name, String image) {
      this(null, userKlaeff,name,image);
  }

  public UserKlaeff getUser() {
    return userKlaeff;
  }

  public String getName() {
    return name.name();
  }

  public String getImage() {
    return image;
  }
}
