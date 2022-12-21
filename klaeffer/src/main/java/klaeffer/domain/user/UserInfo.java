package klaeffer.domain.user;

import klaeffer.domain.shared.KlaeffUser;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;

public class UserInfo {
  @Id
  private final Integer id;
  private final KlaeffUser klaeffUser;
  private final String name;
  private final String image;

  @PersistenceCreator
  public UserInfo(Integer id, KlaeffUser klaeffUser, String name, String image) {
    this.id = id;
    this.klaeffUser = klaeffUser;
    this.name = name;
    this.image = image;
  }
  public UserInfo(KlaeffUser klaeffUser, String name, String image){
    this(null,klaeffUser,name,image);
  }

  public KlaeffUser getUser() {
    return klaeffUser;
  }

  public String getName() {
    return name;
  }

  public String getImage() {
    return image;
  }
}
