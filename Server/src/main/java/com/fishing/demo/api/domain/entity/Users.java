package com.fishing.demo.api.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "set")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "users_id")
    private Long usersId;

    @Column(name = "nickname", nullable = false)
    private String nickname;

    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "profileimage_path", nullable = false)
    private String profileImagePath;

    @Column(name = "created_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    // Manually define the builder method
    public static UsersBuilder builder() {
        return new UsersBuilder();
    }

    public static class UsersBuilder {
        private Long usersId;
        private String nickname;
        private String id;
        private String password;
        private String profileImagePath;
        private Date createdDate;

        UsersBuilder() {
        }

        public UsersBuilder usersId(Long usersId) {
            this.usersId = usersId;
            return this;
        }

        public UsersBuilder nickName(String nickName) {
            this.nickname = nickName;
            return this;
        }

        public UsersBuilder id(String id) {
            this.id = id;
            return this;
        }

        public UsersBuilder password(String password) {
            this.password = password;
            return this;
        }

        public UsersBuilder profileImagePath(String profileImagePath) {
            this.profileImagePath = profileImagePath;
            return this;
        }

        public UsersBuilder createdDate(Date createdDate) {
            this.createdDate = createdDate;
            return this;
        }

        // Add this method to set the userId
        public UsersBuilder userId(String userId) {
            this.id = userId;
            return this;
        }

        public Users build() {
            return new Users(usersId, nickname, id, password, profileImagePath, createdDate);
        }
    }

   // Add this method to the Users entity class
    public Long getUserId() {
        return this.usersId;
    }
}
