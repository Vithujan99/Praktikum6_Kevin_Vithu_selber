package klaeffer.service;

import java.util.List;
import klaeffer.domain.klaeff.Klaeff;

public record KlaeffPage(List<KlaeffDetail> klaeffs, boolean more) {


}
