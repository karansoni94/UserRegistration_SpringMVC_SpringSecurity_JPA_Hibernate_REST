package com.userregistration.model;

public class SecurityQARequest {
    Integer questionId;
    String answer;

    public SecurityQARequest() {
    }

    public SecurityQARequest(Integer questionId, String answer) {
        this.questionId = questionId;
        this.answer = answer;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public SecurityQA convertToSecurityQA(){
        return new SecurityQA(this.questionId, this.answer);
    }
}
