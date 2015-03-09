package com.springapp.mvc;

/**
 * Created by raphael zielinski on 2/5/2015.
 */
public class CustomExceptionHandler extends RuntimeException {
        private String Message;

        public CustomExceptionHandler(String Message) {
            this.Message = Message;
        }

        public String getMessage(){
            return this.Message;
        }

        public void setMessage(String Message) {
            this.Message = Message;
        }


}





