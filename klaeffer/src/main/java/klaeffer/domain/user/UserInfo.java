package klaeffer.domain.model;

import klaeffer.domain.model.User;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;

public class UserInfo {
  @Id
  private final Integer id; // new
  private final User user;
  private final UserName name;
  private final String image;

  @PersistenceCreator
  public UserInfo(Integer id,User user, String name, String image) {
    this.id = id;
    this.user = user;
    this.name = new UserName(name);
    this.image = image;
  }

  public UserInfo(User user,String name, String image) {
      this(null,user,name,image);
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
