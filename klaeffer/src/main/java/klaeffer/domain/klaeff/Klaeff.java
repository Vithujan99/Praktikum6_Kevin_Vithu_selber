package klaeffer.domain.klaeff;

import klaeffer.domain.shared.KlaeffUser;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;

public class Klaeff {

  @Id
  private final Integer id;
  private final KlaeffUser klaeffUser;
  private final String content;

  @PersistenceCreator
  public Klaeff(Integer id, KlaeffUser klaeffUser, String content) {
    this.id = id;
    this.klaeffUser = klaeffUser;
    this.content = content;
  }

  public Klaeff(KlaeffUser klaeffUser, String content) {
    this(null, klaeffUser,content);
  }

  public KlaeffUser getUser() {
    return klaeffUser;
  }

  public String getContent() {
    return content;
  }
}
