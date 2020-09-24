package dto;

public class ExceptionDTO {

    private int code;
    private String message;

    public ExceptionDTO(int code, String description) {
        this.code = code;
        this.message = description;
        if (code == 500) {
            String msg = " : We are sorry for the inconvenience";
            this.message = description + msg;
        }
    }

}
