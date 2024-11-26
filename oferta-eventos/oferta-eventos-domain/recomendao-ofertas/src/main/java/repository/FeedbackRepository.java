package repository;

import entity.Feedback;
import valueobjects.IdFeedback;

import java.util.Optional;

public interface FeedbackRepository {

    Feedback save(Feedback feedback);
    Optional<Feedback> buscarFeedbackPorId(IdFeedback idFeedback);

}
