package klaeffer.domain.klaeff;

import klaeffer.domain.shared.User;

public class Klaeff {

  private final User user;
  private final Content content;

  public Klaeff(User user, String content) {
    this.user = user;
    this.content = new Content(content);
  }

  public User getUser() {
    return user;
  }

  public String getContent() {
    return content.content();
  }
}
