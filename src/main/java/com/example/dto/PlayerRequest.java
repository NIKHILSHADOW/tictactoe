package com.example.dto;

public class PlayerRequest {
    private String name;
    private String email;
    private Byte[] photo;

    private PlayerRequest() {
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String name;
        private String email;
        private Byte[] photo;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder photo(Byte[] photo) {
            this.photo = photo;
            return this;
        }

        public PlayerRequest build() {
            PlayerRequest playerRequest = new PlayerRequest();
            playerRequest.email = this.email;
            playerRequest.name = this.name;
            playerRequest.photo = this.photo;
            return playerRequest;
        }

    }

}
