package sv.edu.udb.www.Recursos.Models;

public class StatusResponseIntern {

        private String message;
        private int statusCode;

        public StatusResponseIntern(String status, int code) {
            message = status;
            statusCode = code;
        }
        public String getMessage(){
            return message;
        }
        public int getCode(){
            return statusCode;
        }

        public String StatusCode(){
            String jsonResponse = "{\"statusCode\":" + getCode() + ", \"message\":\"" + getMessage() + "\"}";
            return jsonResponse;
        }


}
