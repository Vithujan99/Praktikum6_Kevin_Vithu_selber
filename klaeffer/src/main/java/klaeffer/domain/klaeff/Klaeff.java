package klaeffer.domain.klaeff;

import klaeffer.domain.shared.UserKlaeff;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;

public class Klaeff {
  @Id
  private final Integer id;
  private final UserKlaeff userKlaeff;
  private final Content content;
  @PersistenceCreator
  public Klaeff(Integer id, UserKlaeff userKlaeff, String content) {
    this.id = id;
    this.userKlaeff = userKlaeff;
    this.content = new Content(content);
  }

  public Klaeff(UserKlaeff userKlaeff, String content) {
    this(null, userKlaeff, content);
  }

  public UserKlaeff getUser() {
    return userKlaeff;
  }

  public String getContent() {
    return content.content();
  }
}
