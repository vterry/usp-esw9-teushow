package entity;

import valueobjects.IdFeedback;

public class Feedback extends BaseEntity<IdFeedback> {
    private String comentario;
    private int nota;

    public Feedback(IdFeedback idFeedback, String comentario, int nota){
        super.setId(idFeedback);
        this.comentario = comentario;
        this.nota = nota;
    }

    public String getComentario(){
        return this.comentario;
    }

    public int getNota(){
        return this.nota;
    }
}
