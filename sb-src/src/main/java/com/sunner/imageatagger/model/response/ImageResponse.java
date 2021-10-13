package com.sunner.imageatagger.model.response;

public class ImageResponse {
    private Result result;
    private Status status;

    public Result getResult() {
        return result;
    }

    public Status getStatus() {
        return status;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return
                "ImageResponse{" +
                        "result = '" + result + '\'' +
                        ",status = '" + status + '\'' +
                        "}";
    }

    public void listResults() {
		this.getResult().getCategories().forEach(System.out::print);
    }
}
