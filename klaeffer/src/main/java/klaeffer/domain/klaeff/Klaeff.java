package klaeffer.domain.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;

public class Klaeff {
  @Id
  private final Integer id;
  private final User user;
  private final Content content;
  @PersistenceCreator
  public Klaeff(Integer id, User user, String content) {
    this.id = id;
    this.user = user;
    this.content = new Content(content);
  }

  public Klaeff(User user, String content) {
    this(null, user, content);
  }

  public User getUser() {
    return user;
  }

  public String getContent() {
    return content.content();
  }
}
